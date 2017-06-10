import sys

f = open("src/main/java/hello/VersionNumber.java", "w+")

f.write("package hello;\n")
f.write("public final class VersionNumber {\n")
f.write("private static final String versionNumber = \"" + sys.argv[1]+"\";\n")
f.write("public static String getVersionNumber() {\n")
f.write("return versionNumber;\n")
f.write("}\n")
f.write("private VersionNumber(){}\n")
f.write("}\n")