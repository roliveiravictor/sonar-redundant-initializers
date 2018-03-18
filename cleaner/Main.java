package cleaner;

import cleaner.utils.FileUtils;
import java.io.File;
import java.util.ArrayList;

import cleaner.domain.Directory;
import cleaner.domain.Resource;
import cleaner.model.FileRunnerModel;
import cleaner.utils.LineUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Integer linesCounter = 0;
    
    public static void main(String[] args) {
        try {
            ArrayList<File> classes = getClasses();
            clean(classes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ArrayList<File> getClasses(){
        Directory directoryClass = new Directory();
        Directory directoryLayout = new Directory();

        directoryClass.setPath("C:/Users/v/Documents/Scrip Test");
        directoryLayout.setPath("C:/Users/v/Documents/Scrip Test");

        FileUtils classes = new FileUtils();

        ArrayList<File> project = new ArrayList<File>();
        project.addAll(classes.walkDirs(directoryClass.getPath()));
        project.addAll(classes.walkDirs(directoryLayout.getPath()));
        
        System.out.println("Classes and Layouts: "+project.size() + "\n");
        
        return project;
    }

    private static void checkResources() {
        Directory directoryRes = new Directory();

        directoryRes.setPath("C:/Users/v/Documents/Scrip Test");

        FileUtils res = new FileUtils();

        ArrayList<Resource> filesName = new ArrayList<Resource>();
        filesName.addAll(res.getResource(directoryRes.getPath()));
        
        System.out.println("Resources: "+filesName.size());
    }

    private static void clean(ArrayList<File> classes) throws FileNotFoundException, IOException {
        for(File clazz : classes) {
            if(fileNeedsModification(clazz)){
                StringBuilder fileBuilder = readFileModifications(clazz);
                writeFileModifications(clazz, fileBuilder);
            }
        }
        
        System.out.println("Lines Modified: " + linesCounter);
    }

    private static boolean hasLinesToReadInFile(FileRunnerModel fileRunnerModel) {
        try {
            String text = fileRunnerModel.getReader().readLine();
            fileRunnerModel.setLine(text);
            return text != null;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private static StringBuilder readFileModifications(File clazz) throws FileNotFoundException {
        FileReader fileReader = new FileReader(clazz);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            FileRunnerModel fileRunnerModel = new FileRunnerModel();
            fileRunnerModel.setReader(bufferedReader);
            
            StringBuilder fileBuilder = new StringBuilder();
            
            while (hasLinesToReadInFile(fileRunnerModel)) {
                if(LineUtils.isPrimitiveTarget(fileRunnerModel.getLine())){
                    System.out.println("Target Line: " + fileRunnerModel.getLine());
                    
                    String modifiedLine = LineUtils.clean(fileRunnerModel.getLine());
                    fileBuilder.append(modifiedLine + "\n");
                    
                    System.out.println("Modified Line: " + modifiedLine  + "\n");
                    
                    linesCounter++;
                } else {
                    fileBuilder.append(fileRunnerModel.getLine() + "\n");
                }
            }
            
        return fileBuilder;
    }

    private static void writeFileModifications(File clazz, StringBuilder fileBuilder) throws IOException {
        FileWriter fileWriter = new FileWriter(clazz);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileBuilder.toString());
            bufferedWriter.close();
    }

    private static boolean fileNeedsModification(File clazz) throws FileNotFoundException {
            FileReader fileReader = new FileReader(clazz);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            FileRunnerModel fileRunnerModel = new FileRunnerModel();
            fileRunnerModel.setReader(bufferedReader);
            
            while (hasLinesToReadInFile(fileRunnerModel)) {
                if(LineUtils.isPrimitiveTarget(fileRunnerModel.getLine())){    
                    return true;
                }
            }
            
            return false;
    }
}
