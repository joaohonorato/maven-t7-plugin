package com.googlecode.t7mp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(IOUtils.class)
public class StaticTomcatConfiguratorTest {
	
//	private File catalinaBaseDir;
//	private static int counter = 1;
//	
//	private Log log = Mockito.mock(Log.class);
//	
//	@Before
//	public void setUp(){
//		catalinaBaseDir = new File(new File(System.getProperty("java.io.tmpdir")), "catalinaBase_" + (++counter));
//		catalinaBaseDir.mkdirs();
//		catalinaBaseDir.deleteOnExit();
//	}
//	
//	@After
//	public void tearDown() throws IOException{
//		FileUtils.deleteDirectory(catalinaBaseDir);
//		if(catalinaBaseDir.exists()){
//			System.err.println("Could not delete directory " + catalinaBaseDir.getAbsolutePath());
//		}
//	}
//	
//	@Test(expected=MojoExecutionException.class)
//	public void testMockIOUtil() throws Exception{
//		PowerMockito.mockStatic(IOUtils.class);
//		SetupUtil util = Mockito.mock(SetupUtil.class);
//		PowerMockito.when(IOUtils.class, "copy", Mockito.any(InputStream.class), Mockito.any(OutputStream.class)).thenThrow(new IOException("Mock exception"));
////		Mockito.when(IOUtils.copy(Mockito.any(InputStream.class), Mockito.any(OutputStream.class))).thenThrow(new IOException());
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, util);
//		configurator.createTomcatDirectories();
//		configurator.copyDefaultConfig();
//		PowerMockito.verifyStatic(Mockito.times(0));
//		IOUtils.copy(Mockito.any(InputStream.class), Mockito.any(OutputStream.class));
//	}

}
