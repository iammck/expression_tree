cls
Remove-Item bin\*;
javac `@argfile `@testArgfile;
java -cp bin`;lib\* MyTestRunner