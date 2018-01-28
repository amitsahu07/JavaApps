package com.xo.remote;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteFileDeletor {

	public static void main(String[] args) {
		String user = "kafka_replicator";
		String password = "ABcd12345";
		String host = "bidakfkapd-021.exelonds.com";
		
		//String filepath = "/tmp/test/";
		//String fileName = "markfordetetion";
		String filepath = args[0];
		String fileName = args[1];
		ChannelSftp channelSftp = null;
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.connect();

			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
			// log.info("SFTP Channel created.");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(filepath);
			
			Vector filelist = channelSftp.ls(filepath);
			for (int i = 0; i < filelist.size(); i++) {
				LsEntry entry = (LsEntry) filelist.get(i);
				String s = entry.getFilename().toString();
				
				
				if (entry.getFilename().equals(fileName) ) {
						channelSftp.rm(filepath+fileName);
						System.out.println("Warning: Removed File: "+fileName);
				}
			}
			session.disconnect();
		} catch (Exception e) {
			System.err.print(e);

		}
	}

}
