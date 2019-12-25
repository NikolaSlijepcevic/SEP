package com.sep.bitcoin;

import java.io.File;
import java.io.IOException;





import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bitcoinj.core.*;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.store.UnreadableWalletException;

public class Wallet {

			public Wallet() {
			
	 		// work with testnet
	        final NetworkParameters netParams = NetworkParameters.testNet();
	
	        // Try to read the wallet from storage, create a new one if not possible.
	        org.bitcoinj.core.Wallet wallet = null;
	        
	        File walletFile = new File("test.wallet");
	        
	        try {
	        	// load wallet from file
	        	            wallet = wallet.loadFromFile(walletFile);
	        	            
	            //wallet = new org.bitcoinj.core.Wallet(netParams);
	            
	            
	            // create a key and add it to the wallet
	            //wallet.addKey(new ECKey());
	            
	            // save wallet contents to disk
	            //wallet.saveToFile(walletFile);
	            
	        } catch (UnreadableWalletException e) {
	            System.out.println("Unable to create wallet file.");
	        }
	        
	        // fetch the first key in the wallet directly from the keychain ArrayList
	        ECKey firstKey = wallet.getImportedKeys().get(0);
	
	        // output key 
	        System.out.println("First key in the wallet:\n" + firstKey);
	        
	        // and here is the whole wallet
	        System.out.println("Complete content of the wallet:\n" + wallet);
	        
	        // we can use the hash of the public key
	        // to check whether the key pair is in this wallet
	        if (wallet.isPubKeyHashMine(firstKey.getPubKeyHash())) {
	            System.out.println("Yep, that's my key.");
	        } else {
	            System.out.println("Nope, that key didn't come from this wallet.");
	        }
			
			}
			
			public void transaction() {
				// we get the following from the command line ...
				        // (this is not secure - needs validation)
				        String network          = "test";  // "test" or "prod"
				        String walletFileName   = "test.wallet";  // wallet file name
				        String amountToSend     = "1";  // milli-BTC
				        String recipient        = "mxGPEhy2Q5hSGbb5JRHjvoN4jKGjYDGHHB ";  // Bitcoin address
				
				        // the Bitcoin network to use
				        final NetworkParameters netParams;
				        
				        // check for production Bitcoin network ...
				        if (network.equalsIgnoreCase("prod")) {
				            netParams = NetworkParameters.prodNet();
				        // ... otherwise use the testnet
				        } else {
				            netParams = NetworkParameters.testNet();
				        }
				
				        // data structure for block chain storage
				        BlockStore blockStore = new MemoryBlockStore(netParams);
				
				        // declare object to store and understand block chain
				        BlockChain chain;
				        
				        // declare wallet
				        org.bitcoinj.core.Wallet wallet;
				        
				        try {
				            
				            // wallet file that contains Bitcoins we can send
				            final File walletFile = new File(walletFileName);
				
				            // load wallet from file
				            wallet = wallet.loadFromFile(walletFile);
				            
				            // how man milli-Bitcoins to send
				            BigInteger btcToSend = new BigInteger(amountToSend);
				                    
				            // initialize BlockChain object
				            chain = new BlockChain(netParams, wallet, blockStore);
				
				            // instantiate Peer object to handle connections
				            final Peer peer = new Peer(netParams, new PeerAddress(InetAddress.getLocalHost()), chain);
				
				            // connect to peer node on localhost
				            peer.connect();
				            
				            // recipient address provided by official Bitcoin client
				            Address recipientAddress = new Address(netParams, recipient);
				
				            // tell peer to send amountToSend to recipientAddress
				            Transaction sendTxn = wallet.sendCoins(peer, recipientAddress, btcToSend);
				            
				            // null means we didn't have enough Bitcoins in our wallet for the transaction
				           if (sendTxn == null) {
				                System.out.println("Cannot send requested amount of " + btcToSend
				                                + " BTC; wallet only contains " + wallet.getBalance() + " BTC.");
				            } else {
				                
				                // once communicated to the network (via our local peer),
				                // the transaction will appear on Bitcoin explorer sooner or later
				                System.out.println(btcToSend + " BTC sent. You can monitor the transaction here:\n"
				                                + "http://blockexplorer.com/tx/" + sendTxn.getHashAsString());
				            }
				
				            // save wallet with new transaction(s)
				            wallet.saveToFile(walletFile);
				
				        // handle the various exceptions; this needs more work
				        } catch (BlockStoreException e) {
				            e.printStackTrace();
				        } catch (UnknownHostException e) {
				            e.printStackTrace();
				        } catch (PeerException e) {
				            e.printStackTrace();
				        } catch (AddressFormatException e) {
				            e.printStackTrace();
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				    }
}		

