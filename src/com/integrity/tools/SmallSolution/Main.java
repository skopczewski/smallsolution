package com.integrity.tools.SmallSolution;

import mks.ci.common.Solution;
import mks.frame.server.viewset.ViewSet;
import mks.frame.server.viewset.ViewSetPermission;
import mks.frame.server.viewset.ViewSetPermissionList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            showHelp();
            return;
        } else if (args.length > 3) {
            System.out.println("Maximum 3 arguments are allowed.");
            showHelp();
            return;
        }

        String inputFile = null;
        String user = null;
        String outputFile = null;

        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                inputFile = args[i];
            } else if (i == 1) {
                user = args[i];
            } else {
                outputFile = args[i];
            }
        }

        System.out.print("Reading solution... ");

        Solution sol = Solution.load(new File(inputFile));

        System.out.println("Completed");

        if (outputFile != null) {
            System.out.println("Editing solution " + sol.getDescription());
        } else {
            System.out.println("Viewing solution " + sol.getDescription());
        }

        List viewSetList = sol.getPlan().getViewSetStagingPackage().getNewObjects();

        System.out.println("ViewSets:");

        for (Object aViewSetList : viewSetList) {
            ViewSet viewSet = (ViewSet) aViewSetList;
            System.out.println(viewSet.getName());

            if (user != null) {
                ViewSetPermissionList permissions = viewSet.getPermissions();

                System.out.println("Already set users " + permissions.getUsers());

                if (outputFile != null) {
                    System.out.println("Adding user " + user);
                    permissions.addUser(user, ViewSetPermission.MODIFY_PERMISSION);
                }
            }
        }

        if ( (user != null) && (outputFile != null) ) {
            System.out.print("Generating solution " + outputFile + " ... ");
            sol.store(new File(outputFile));
            System.out.println("Completed");
        }
    }

    private static void showHelp() {
        System.out.println("Tool will add username to all ViewSets with modify permission.");
        System.out.println();
        System.out.println("Location: Put this JAR file in ILM Client installation folder.");
        System.out.println();
        System.out.println("Usage: [input file] [username] [output file]");
        System.out.println();
        System.out.println("Example 1: java -jar SmallSolution.jar MySolution.imt");
        System.out.println("\t- List all ViewSets in MySolution.imt");
        System.out.println();
        System.out.println("Example 2: java -jar SmallSolution.jar MySolution.imt administrator");
        System.out.println("\t- List all ViewSets and already set users in MySolution.imt");
        System.out.println();
        System.out.println("Example 3: java -jar SmallSolution.jar MySolution.imt administrator NewSolution.imt");
        System.out.println("\t- List all ViewSets and already set users in MySolution.imt");
        System.out.println("\t- Add administrator to users with Modify permission");
        System.out.println("\t- Generate new solution NewSolution.imt");
    }
}
