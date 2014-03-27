package com.gwccnet.utilities.generation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.gwccnet.utilities.CommonUtils;

public class ObjectFactoryGenerator {
	private String packageToScan;
	private String filePath;
	
	public ObjectFactoryGenerator() {
	}

	@SuppressWarnings({ "static-access", "rawtypes" })
	public void generate() throws Exception {
		if(CommonUtils.isNullOrEmpty(this.getPackageToScan())) {
			throw new IllegalArgumentException("Package to scan is empty");
		}
		
		if(CommonUtils.isNullOrEmpty(this.getFilePath())) {
			throw new IllegalArgumentException("File path is empty");
		}
		
		PrintWriter pw = new PrintWriter(new File(this.getFilePath()));
		
		pw.println("package " + this.getPackageToScan() + ";");
		pw.println();
		
		pw.println("import javax.xml.bind.annotation.XmlRegistry;");
		pw.println();
		
		pw.println("@XmlRegistry");
		pw.println("public class ObjectFactory {");
			
		List<Class<?>> classList = this.getClassesForPackage(this.getPackageToScan());
		
		boolean firstIteration = true;
		
		for(Class clazz : classList) {
			if(clazz.getSimpleName().equals("ObjectFactory") ||
			   clazz.getSimpleName().equals("package-info") ||
			   clazz.isInterface() ||
			   clazz.isMemberClass() ||
			   clazz.isEnum()) {
				continue;
			}
			
			try {
				clazz.newInstance();
			} catch(Throwable t) {
				//Quick and dirty to filter out classes without no-arg constructor
				continue;
			}
			
			if(firstIteration) {
				firstIteration = false;
			} else {
				pw.println();
			}
			
			String line = String.format("\tpublic %s create%s() {", clazz.getSimpleName(), clazz.getSimpleName());
			pw.println(line);
			
			line = String.format("\t\treturn new %s();", clazz.getSimpleName());
			pw.println(line);
			
			line = "\t}";
			pw.println(line);
		}
		
		pw.println("}");
		
		pw.flush();
		pw.close();
	}
	
	//Base on this post from stackoverflow
	//http://stackoverflow.com/questions/176527/how-can-i-enumerate-all-classes-in-a-package-and-add-them-to-a-list
	private static ArrayList<Class<?>> getClassesForPackage(String pkgname) {
	    ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
	    // Get a File object for the package
	    File directory = null;
	    String fullPath;
	    String relPath = pkgname.replace('.', '/');
	    System.out.println("ClassDiscovery: Package: " + pkgname + " becomes Path:" + relPath);
	    URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
	    System.out.println("ClassDiscovery: Resource = " + resource);
	    if (resource == null) {
	        throw new RuntimeException("No resource for " + relPath);
	    }
	    fullPath = resource.getFile();
	    System.out.println("ClassDiscovery: FullPath = " + resource);

	    try {
	        directory = new File(resource.toURI());
	    } catch (URISyntaxException e) {
	        throw new RuntimeException(pkgname + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
	    } catch (IllegalArgumentException e) {
	        directory = null;
	    }
	    System.out.println("ClassDiscovery: Directory = " + directory);

	    if (directory != null && directory.exists()) {
	        // Get the list of the files contained in the package
	        String[] files = directory.list();
	        for (int i = 0; i < files.length; i++) {
	            // we are only interested in .class files
	            if (files[i].endsWith(".class")) {
	                // removes the .class extension
	                String className = pkgname + '.' + files[i].substring(0, files[i].length() - 6);
	                System.out.println("ClassDiscovery: className = " + className);
	                try {
	                    classes.add(Class.forName(className));
	                } 
	                catch (ClassNotFoundException e) {
	                    throw new RuntimeException("ClassNotFoundException loading " + className);
	                }
	            }
	        }
	    }
	    else {
	        try {
	            String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
	            JarFile jarFile = new JarFile(jarPath);         
	            Enumeration<JarEntry> entries = jarFile.entries();
	            while(entries.hasMoreElements()) {
	                JarEntry entry = entries.nextElement();
	                String entryName = entry.getName();
	                if(entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/".length())) {
	                    System.out.println("ClassDiscovery: JarEntry: " + entryName);
	                    String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
	                    System.out.println("ClassDiscovery: className = " + className);
	                    try {
	                        classes.add(Class.forName(className));
	                    } 
	                    catch (ClassNotFoundException e) {
	                        throw new RuntimeException("ClassNotFoundException loading " + className);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            throw new RuntimeException(pkgname + " (" + directory + ") does not appear to be a valid package", e);
	        }
	    }
	    return classes;
	}
	
	public String getPackageToScan() {
		return packageToScan;
	}

	public void setPackageToScan(String packageToScan) {
		this.packageToScan = packageToScan;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
