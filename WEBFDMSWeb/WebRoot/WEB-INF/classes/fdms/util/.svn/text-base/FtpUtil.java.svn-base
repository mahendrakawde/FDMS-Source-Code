package fdms.util;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FtpUtil {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FtpUtil() {
		System.out.println("Inside FtpUtil constructor...........: ");
	}

//	public FtpUtilParth() {
//		System.out.println("Inside FtpUtil constructor...........: " + str);
//	}
	/**
	 * Upload a file to a FTP server. A FTP URL is generated with the following
	 * syntax: ftp://user:password@host:port/filePath;type=i.
	 * 
	 * @param ftpServer
	 *            , FTP server address (optional port ':portNumber').
	 * @param user
	 *            , Optional user name to login.
	 * @param password
	 *            , Optional password for user.
	 * @param fileName
	 *            , Destination file name on FTP server (with optional preceding
	 *            relative path, e.g. "myDir/myFile.txt").
	 * @param source
	 *            , Source file to upload.
	 * @throws MalformedURLException
	 *             , IOException on error.
	 */
	public void upload(String ftpServer, String user, String password,
			String fileName, File source) throws MalformedURLException,
			IOException {
		if (ftpServer != null && fileName != null && source != null) {
			StringBuffer sb = new StringBuffer("ftp://");
			// check for authentication else assume its anonymous access.
			if (user != null && password != null) {
				sb.append(user);
				sb.append(':');
				sb.append(password);
				sb.append('@');
			}
			sb.append(ftpServer);
			sb.append('/');
			sb.append(fileName);
			/*
			 * type ==> a=ASCII mode, i=image (binary) mode, d= file directory
			 * listing
			 */
			sb.append(";type=i");

			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			try {

				System.out.println("Trying to connect to server.......");

				URL url = new URL(sb.toString());
				URLConnection urlc = url.openConnection();

				System.out.println("Server connected...");

				bos = new BufferedOutputStream(urlc.getOutputStream());
				bis = new BufferedInputStream(new FileInputStream(source));

				int i;
				// read byte by byte until end of stream
				while ((i = bis.read()) != -1) {
					bos.write(i);
				}

				System.out.println("FIle uploaded to FTP.....");

			} finally {
				if (bis != null)
					try {
						bis.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				if (bos != null)
					try {
						bos.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
			}
		} else {
			System.out.println("Input not available.");
		}
	}

	/**
	 * Upload file on FTP server. It will generate directory path if not exist
	 * on FTP & upload file in that particular directory.
	 * 
	 * @param ftpHost
	 *            FTP host name
	 * @param ftpPort
	 *            Port on which to connect FTP server
	 * @param ftpUser
	 *            FTP user name
	 * @param ftpPassword
	 *            Password to access FTP server
	 * @param fileName
	 *            File name to be uploaded
	 * @param pathOnLocal
	 *            Path of local environment from which file to be fetched &
	 *            uploaded to FTP server.
	 * @param pathOnServer
	 *            Path on which the file to be uploaded on FTP server
	 * 
	 */

	public static void upload(String ftpHost, int ftpPort, String ftpUser,
			String ftpPassword, String fileName, String pathOnLocal,
			String pathOnServer) {
		
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();

			ftpClient.connect(ftpHost, ftpPort);

			ftpClient.login(ftpUser, ftpPassword);

			try {
				// Creates directory on FTP server to upload file.
				ftpClient.createDirectory(pathOnServer);
			} catch (FTPException ftpe) {
				// If the directory already exists then it will fire an
				// FTPException & error code as 550.
				System.out.println("ftpe.getCode(): " + ftpe.getCode());
				System.out.println("ftpe.getMessage(): " + ftpe.getMessage());
			}

			ftpClient.changeDirectory(pathOnServer);
			
			File localFile = new File(pathOnLocal + fileName);

			ftpClient.upload(localFile);
			
			if(localFile.delete()) {
				System.out.println("File deleted: ");
			}

			System.out.println("Current Dir: " + ftpClient.currentDirectory());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(ftpClient != null) {
				try {
					ftpClient.disconnect(true);
				} catch (Exception ex) {
					System.out.println("FtpUtil.upload: FtpClient can't disconnect: " + ex.getMessage());
				}
			}
		}
	}

}
