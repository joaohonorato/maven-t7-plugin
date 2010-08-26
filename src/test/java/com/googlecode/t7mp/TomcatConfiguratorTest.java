package com.googlecode.t7mp;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

public class TomcatConfiguratorTest {
	
	private File catalinaBaseDir;
	private static int counter = 1;
	
	private Log log = Mockito.mock(Log.class);
	private SetupUtil setupUtil = Mockito.mock(SetupUtil.class);
	
	private List<String> expectedDirectoryNames = Arrays.asList(new String[]{"conf","lib", "logs", "temp", "webapps", "work"});
	private List<String> expectedFileNames = Arrays.asList(new String[]{"context.xml", "server.xml", "catalina.properties","catalina.policy", "logging.properties", "tomcat-users.xml", "web.xml"});
	@Before
	public void setUp(){
		catalinaBaseDir = new File(new File(System.getProperty("java.io.tmpdir")), "catalinaBase_" + (++counter));
		catalinaBaseDir.mkdirs();
		catalinaBaseDir.deleteOnExit();
	}
	
	@After
	public void tearDown() throws IOException{
		FileUtils.deleteDirectory(catalinaBaseDir);
		if(catalinaBaseDir.exists()){
			System.err.println("Could not delete directory " + catalinaBaseDir.getAbsolutePath());
		}
	}
	
//	@Test
//	public void testConfigurator() throws MojoExecutionException{
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, setupUtil);
//		configurator.createTomcatDirectories();
//		File[] createdDirectories = catalinaBaseDir.listFiles(new FileFilter(){
//			@Override
//			public boolean accept(File file) {
//				return file.isDirectory();
//			}
//		});
//		List<String> directoryNames = new ArrayList<String>();
//		for(File directory : createdDirectories) {
//			directoryNames.add(directory.getName());
//		}
//		Collections.sort(expectedDirectoryNames);
//		Collections.sort(directoryNames);
//		Assert.assertEquals(expectedDirectoryNames, directoryNames);
//	}
	
//	@Test
//	public void testConfiguratorDefaultConfigFiles() throws MojoExecutionException{
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, setupUtil);
//		configurator.createTomcatDirectories();
//		configurator.copyDefaultConfig();
//		File confDir = new File(catalinaBaseDir, "/conf/");
//		File[] createdDirectories = confDir.listFiles(new FileFilter(){
//			@Override
//			public boolean accept(File file) {
//				return file.isFile();
//			}
//		});
//		List<String> fileNames = new ArrayList<String>();
//		for(File file : createdDirectories) {
//			fileNames.add(file.getName());
//		}
//		Collections.sort(expectedFileNames);
//		Collections.sort(fileNames);
//		Assert.assertEquals(expectedFileNames, fileNames);
//	}
	
//	@Test(expected=TomcatSetupException.class)
//	public void testDirectoryNotExistAndCouldNotCreated() throws MojoExecutionException{
//		File catalinaBaseDir = new File("/catalinaBase");
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, Mockito.mock(SetupUtil.class));
//		configurator.createTomcatDirectories();
//	}
//	
//	@Test(expected=TomcatSetupException.class)
//	public void testDirectoryNotExistAndCouldNotCreatedForConfigfiles() throws MojoExecutionException, IOException{
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, Mockito.mock(SetupUtil.class));
//		configurator.createTomcatDirectories();
//		FileUtils.deleteDirectory(catalinaBaseDir);
//		configurator.copyDefaultConfig();
//	}
	
	@Test(expected=TomcatSetupException.class)
	public void testUserConfigDirDoesNotExist() throws MojoExecutionException{
		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, Mockito.mock(SetupUtil.class));
		configurator.copyUserConfigs(new File("/"));
	}
	
	@Ignore
	@Test(expected=TomcatSetupException.class)
	public void testUserConfigDirIsNotADirectory() throws MojoExecutionException, IOException{
		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, Mockito.mock(SetupUtil.class));
		configurator.copyUserConfigs(File.createTempFile("test_", "tmp"));
	}
	
	@Test
	public void testNoUserconfigDirConfigured() throws MojoExecutionException{
		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, setupUtil);
		configurator.copyUserConfigs(null);
	}
	

	
	@Test(expected=TomcatSetupException.class)
	public void testCopyConfigResourceWithIOException() throws IOException{
		SetupUtil setupUtil = Mockito.mock(SetupUtil.class);
		Mockito.doThrow(new IOException("TESTEXCEPTION")).when(setupUtil).copy(Mockito.any(InputStream.class), Mockito.any(OutputStream.class));
		TomcatConfigurator configurator = new TomcatConfigurator(new File("/klaus"), log, setupUtil);
		configurator.copyConfigResource("test");
	}
	
	public static class ExceptionalSetupUtil implements SetupUtil {

		@Override
		public void copy(InputStream inputStream, OutputStream outputStream)
				throws IOException {
			throw new IOException("TESTIOEXCEPTION");
		}
		
	}
}
