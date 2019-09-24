package com.link.demopac.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 *
 */
public class FileUtils {
	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 把message写入到指定folder, 文件名为当前时间戳. yyyyMMddHHmmss_SSS, 如重名再生成3位随机数. yyyyMMddHHmmss_SSS_123
	 * @param filePath
	 * @param message
	 */
	public static void writeMessageToFile(String filePath,String message) {
		if(filePath!=null) {
			File folder = new File(filePath);
	        //文件夹路径不存在
	        if (!folder.exists() && !folder.isDirectory()) {
	            logger.debug("Folder not exists，create the file path:" + filePath);
	            folder.mkdirs();
	        }
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
	        String fileName=simpleDateFormat.format(new Date());
	        File file=new File(filePath + File.separator +fileName);
	        if(file.exists()) {
	        	Random random=new Random();
	        	file=new File(filePath + File.separator +fileName + "_" + random.nextInt(1000));
	        }
			BufferedWriter bw=null;
			try {
				bw=new BufferedWriter(new FileWriter(file));				
				bw.write(message);
				bw.flush();
				logger.debug("Success save the message to file:" + file.getPath());
			}catch(Exception e) {
				logger.error(message);
				logger.error(e.getMessage(),e);
			}finally {
				if(bw!=null) {
					try {
						bw.close();
					} catch (IOException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}else {
			logger.error("Not point the filePath.");
		}
	}
	/**
	 * 列出指定文件夹目录下所有文件, 不包括子目录下文件
	 * @param folderPath
	 * @return
	 */
	public static List<File> listFilesInFolder(String folderPath){	
		return listFilesInFolder(folderPath,null);
	}
	/**
	 * 按照指定的过滤器列出指定文件夹目录下所有文件, 不包括子目录下文件
	 * @param folderPath
	 * @param filter
	 * @return
	 */
	public static List<File> listFilesInFolder(String folderPath, FilenameFilter filter){
		List<File> result=new ArrayList<File>();
		File folder=new File(folderPath);
		if(folder.exists() && folder.isDirectory()) {
			File[] files=(filter==null?folder.listFiles():folder.listFiles(filter));
			if(files!=null && files.length>0) {
				for(File file:files) {
					if(file.exists() && file.isFile()) {
						result.add(file);
					}
				}
			}
		}
		return result;
	}
	/**
	 * 按照指定的过滤器列出指定文件夹目录下所有文件, 不包括子目录下文件, 并按照修改时间排序
	 * @param folderPath
	 * @param filter
	 * @param isAsc true: 升序， false: 降序
	 * @return
	 */
	public static List<File> listFilesInFolder(String folderPath, FilenameFilter filter, boolean isAsc){
		List<File> result=listFilesInFolder(folderPath, filter);
		if(isAsc) {
			Collections.sort(result, new Comparator<File>() {
				   @Override
				   public int compare(File o1, File o2) {
					return Long.compare(o1.lastModified(), o2.lastModified());
				   }
				  });
		}else {
			Collections.sort(result, new Comparator<File>() {
				   @Override
				   public int compare(File o1, File o2) {
					return Long.compare(o2.lastModified(), o1.lastModified());
				   }
				  });
		}
		return result;
	}
	/**
	 * 列出子文件夹，
	 * @param folderPath
	 * @return
	 */
	public static List<File> listSubFolder(String folderPath){
		List<File> result=new ArrayList<File>();
		File folder=new File(folderPath);
		if(folder.exists() && folder.isDirectory()) {
			File[] files=folder.listFiles();
			if(files!=null && files.length>0) {
				for(File file:files) {
					if( file.isDirectory() && file.canRead()) {
						result.add(file);
					}
				}
			}
		}
		return result;
	}
	/**
	 * 读取指定文件中的内容
	 * @param file
	 * @return
	 */
	public static String readFileContent(File file) {
		logger.debug("Begin read file content for " + file.getName());
		StringBuilder sb=new StringBuilder();
		if(file.exists() && file.isFile()) {			
			BufferedReader reader=null;
			try {
				reader=new BufferedReader(new FileReader(file));
				char[] temp=new char[1024];
				int readSize=reader.read(temp);
				while(readSize>0) {
					sb.append(temp,0,readSize);
					readSize=reader.read(temp);
				}
			}catch(Exception e) {
				logger.error(e.getMessage(),e);
			}finally {
				if(reader!=null) {
					try {
						reader.close();
					}catch(Exception e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
		}else {
        	logger.error("File is not exists");
        }
		return sb.toString();
	}
	/**
	 * 读取指定文件中的内容到输出流
	 * @param file
	 * @param os
	 */
	public static void readFileContent(File file, OutputStream os) throws Exception{
        if(file.exists() && file.isFile()) {
        	readFileContent(new FileInputStream(file),os);
        }else {
        	logger.error("File is not exists");
        }
	}

	/**
	 * 读取指定文件中的内容到输出流
	 * @param in
	 * @param os
	 */
	public static void readFileContent(InputStream in, OutputStream os) {
		byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        try {
        	bis = new BufferedInputStream(in);
        	int readSize = bis.read(buff);
        	while (readSize != -1) {
        		os.write(buff, 0, readSize);
        		readSize = bis.read(buff);
        	}
        	os.flush();
        } catch (IOException e) {
        	logger.error(e.getMessage(),e);
        } finally {
        	try {
	        	if (bis != null) {
		            bis.close(); 
	        	}
	        	if(in!=null) {
	        		in.close();
	        	}
	        	if(os!=null) {
	        		os.close();
	        	}
        	} catch (IOException e) {
            	logger.error(e.getMessage(),e);
            }
        }  
	}
	
	
	/**
	 * 移动文件
	 * @param sourceFile
	 * @param targetFolder
	 * @return
	 */
	public static boolean moveFile(File sourceFile,String targetFolder) {
		boolean result=false;
		File targetFileFolder = new File(targetFolder);
        if(!targetFileFolder.exists()){
        	targetFileFolder.mkdirs();
        }
        try {
        	File targetFile=new File(targetFolder + File.separator + sourceFile.getName());
        	sourceFile.renameTo(targetFile);
        	logger.debug("Success moved file from " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
        	result=true;
        }catch(Exception e) {
        	logger.error(e.getMessage(),e);
        }
        return result;
	}
	
	public static void main(String[] args) {
		final String temp="2019";
		List<File> list=listFilesInFolder("D:/tomcat/apache-tomcat-8.5.35/logs" , new FilenameFilter() {
			@Override
		    public boolean accept(File dir, String name) {
		        return name.contains(temp);
		    }
		}, true);
		for(File file:list) {
			System.out.println(file.getName());
		}
	}
}
