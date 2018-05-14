package lab10;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Hotel {

	public static void main(String[] args) {
		System.out.println(Helper.HELP());
		Scanner in = new Scanner(System.in);
		String line;
		String[] commands;
		File dir = new File(System.getProperty("user.dir")).getParentFile();
		Orders orders = new Orders();
		while (true) {
			System.out.print(dir.getAbsolutePath() + "  command: ");
			line = in.nextLine();
			commands = line.split(" ");
			if (commands.length == 0)
				continue;
			else if ("-save".equals(commands[0])
					&& commands.length == 2) {
				File f = new File(dir.getAbsolutePath()
						+ File.separatorChar + commands[1]);
				if (!f.exists() || !f.isFile()) {
					System.err.println("No such file exists");
					continue;
				}
				orders.save(f);
				continue;
			} else if ("-read".equals(commands[0])
					&& commands.length == 2) {
				File f = new File(dir.getAbsolutePath()
						+ File.separatorChar + commands[1]);
				if (!f.exists() || !f.isFile()) {
					System.err.println("No such file exists");
					continue;
				}
				orders.read(f);
				continue;
			} else if ("-cd".equals(commands[0])
					&& commands.length == 2) {
				if (commands[1].equals("..")) {
					dir = dir.getParentFile();
					continue;
				}
				File f = new File(dir.getAbsolutePath()
						+ File.separatorChar + commands[1]);
				if (!f.exists() || !f.isDirectory()) {
					System.err.println("No such dir exists");
					continue;
				}
				dir = f;
				continue;
			} else if ("-dir".equals(commands[0]) && commands.length == 1) {
				for (File f : dir.listFiles())
					System.out.println((f.isDirectory() ? "dir : " : "file: ")
							+ f.getName());
				continue;
			} else if (("-a".equals(commands[0]) || "-add".equals(commands[0]))
					&& commands.length > 5) {
				if (orders.addOrder(
						Arrays.copyOfRange(commands,
								1,
								commands.length))) {
					System.out.println("Order successfully added");
					continue;
				}
			} else if (("-r".equals(commands[0]) || "-remove".equals(commands[0]))
					&& commands.length == 2) {
					try{
						int index = Integer.parseInt(commands[1]);
						if(index > orders.size()) throw new Exception();
						orders.deleteOrder(index - 1);
					} catch (Exception e) {
						System.err.println("WRONG INDEX ARGUMENT");
						System.out.println(Helper.HELP());
						continue;
					}
				System.out.println("Order successfully removed");
				continue;
			} else if (("-s".equals(commands[0]) || "-showAll".equals(commands[0]))
					&& commands.length == 1) {
				System.out.println(orders);
				continue;
			}

			System.out.println(Helper.HELP());

		}
	}

}
