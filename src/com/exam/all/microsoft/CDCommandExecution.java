package com.exam.all.microsoft;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * << descriptive comments>>
 *
 * @author vragi
 * @since <<buildnumber>>
 */
public class CDCommandExecution {

    private final String SLASH = "/";

    public String changeDirectoryString(String currentDirectory, String command) {
        if(currentDirectory == null || command == null || !command.startsWith("cd "))
            throw new IllegalStateException("Invalid inputs");

        command = command.split("cd ")[1];
        if(command.charAt(0) == '/')
            return command;

        List<String> curDirPath = Arrays.asList(currentDirectory.split("/"));
        Deque<String> dirPath = new ArrayDeque<>(curDirPath);
        dirPath.pollFirst();

        String[] commandParts = command.split(SLASH);
        for(String part : commandParts) {
            if(part.equals("..")) {
                dirPath.pollLast();
            } else {
                dirPath.offerLast(part);
            }
        }

        if(dirPath.isEmpty())
            return SLASH;

        StringBuilder result = new StringBuilder();
        for(String dir : dirPath) {
            result.append(SLASH);
            result.append(dir);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        CDCommandExecution c = new CDCommandExecution();
       System.out.println("hi:" + c.changeDirectoryString("/dev/task", "cd /"));
        System.out.println("hi:" + c.changeDirectoryString("/dev/task", "cd .."));
        System.out.println(c.changeDirectoryString("/dev/task", "cd ../.."));
        System.out.println(c.changeDirectoryString("/dev/task", "cd /java"));
        System.out.println(c.changeDirectoryString("/dev/task", "cd java/"));
    }
}
