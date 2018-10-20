import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {

		String remoteHostName = "192.168.0.4";
		String connectLocation = "//" + remoteHostName + "/Hello";

		FSInterface fsinterface = null;

		try {
			System.out.println("Connecting to client at : " + connectLocation);
			fsinterface = (FSInterface) Naming.lookup(connectLocation);
		} catch (Exception e) {
			System.out.println ("Client failed: ");
			e.printStackTrace();
		}

		try {
			while (true) {
                System.out.println("==============File Manager==============");
                System.out.println("==================Menu==================");
                System.out.println("0 - Exit Application;");
                System.out.println("1 - List all files available in the given directory;");
                System.out.println("2 - Create a directory;");
                System.out.println("3 - Create a sub-directory in the given path;");
                System.out.println("4 - Create a new file in the given directory;");
                System.out.println("5 - Delete a file;");
                System.out.println("6 - Write in a file (This will overwrite any information that was previously wrote);");
                System.out.println("7 - Append any information to the end of a file;");
                System.out.println("8 - Read the file information;");

                Scanner keyboard = new Scanner(System.in);

                switch (keyboard.nextInt()) {
                    case 1 :
                        System.out.println("This are all the file in the directory 'C'");
                        System.out.println(Arrays.toString(fsinterface.ls("C:\\")));
                        break;
                    case 2:
                        System.out.println("Inform a path to create a new directory: ");
                        String path = keyboard.next();
                        fsinterface.mkdir(path);
                        break;
                    case 3:
                        System.out.println("Inform a path to create new sub-directory: ");
                        String path2 = keyboard.next();
                        fsinterface.mkdirs(path2);
                        break;
                    case 4:
                        System.out.println("Inform a path to create a new file:");
                        String path3 = keyboard.next();
                        fsinterface.create(path3);
                        break;
                    case 5:
                        System.out.println("Inform the path to the file that will be deleted:");
                        String path4 = keyboard.next();
                        fsinterface.unlink(path4);
                        break;
                    case 6:
                        System.out.println("Inform the path to the file that will receive the information:");
                        String path5 = keyboard.next();
                        System.out.println("Inform the text to be write:");
                        Scanner scanner = new Scanner(System.in);
                        String information = scanner.nextLine();
                        fsinterface.write(information.getBytes(),path5);
                        break;
                    case 7:
                        System.out.println("Inform the path to the file that will append the information to it's end:");
                        String path6 = keyboard.next();
                        System.out.println("Inform the text to be write:");
                        Scanner scanner2 = new Scanner(System.in);
                        String information2 = scanner2.nextLine();
                        fsinterface.append(information2.getBytes(),path6);
                        break;
                    case 8:
                        System.out.println("Inform the path to the file that will be read:");
                        String path7 = keyboard.next();
                        System.out.println(fsinterface.read(path7));
                        break;
                    case 0:
                        System.exit(0);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
