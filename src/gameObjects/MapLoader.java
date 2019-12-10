package gameObjects;

import util.Util;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;


public class MapLoader {

	public static Map load(Path path) {
		ArrayList<Boundary> boundaries = new ArrayList<Boundary>();
		BoundaryFactory factory = new BoundaryFactory();
		ArrayList<Item> items = new ArrayList<Item>();

		File file = new File(path.toString());
		try {
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (!line.startsWith("#")) {

					readLine(line, factory, boundaries, items);
				}

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		items.add(new Teleport(150,150, 1));//add Teleport item
		String name = path.getFileName().toString().substring(0, path.getFileName().toString().indexOf(".map"));
		return new Map(boundaries, items, name);
	}

	private static void readLine(String line, BoundaryFactory factory, ArrayList<Boundary> boundaries, ArrayList<Item> items) {

		int health = 0;
		Rectangle bounds = null;
		String name = null;
		String codeLine = null;
		codeLine = getCode(line);

		if (codeLine.equals("ia")||codeLine.equals("ih")){
			bounds = getHalfBounds(line);
			health = getSpecialAmount(line);
		}
		else{
			if (codeLine.equals("bb")) {
				bounds = getBounds(line);
				health = getSpecialAmount(line);
			}
		}

		switch (codeLine) {
			case "ia":
				items.add(new AmmoPack(bounds.x, bounds.y, health));
				break;
			case "eb": // todo
				break;
			case "ih": // todo
				break;
			default:
				bounds = getBounds(line);
				if (codeLine.equals("bb"))
					health = getSpecialAmount(line);
				boundaries.add(factory.CreateBoundary(codeLine, bounds, health));
				break;

		}

	}

	private static Rectangle getBounds(String line) {

		int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, Util.ordinalIndexOf(line, ",", 1)));
		int y = Integer
				.parseInt(line.substring(Util.ordinalIndexOf(line, ",", 1) + 1, Util.ordinalIndexOf(line, ",", 2)));
		int width = Integer
				.parseInt(line.substring(Util.ordinalIndexOf(line, ",", 2) + 1, Util.ordinalIndexOf(line, ",", 3)));
		int height = Integer.parseInt(line.substring(Util.ordinalIndexOf(line, ",", 3) + 1, line.indexOf(")")));

		return new Rectangle(x, y, width, height);
	}

	private static Rectangle getHalfBounds(String line) {

		int x = Integer.parseInt(line.substring(line.indexOf("(") + 1, Util.ordinalIndexOf(line, ",", 1)));
		int y = Integer.parseInt(line.substring(Util.ordinalIndexOf(line, ",", 1) + 1, line.indexOf(")")));

		return new Rectangle(x, y, 1, 1);
	}

	private static int getSpecialAmount(String line) {
		return Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
	}

	private static String getSpecialStringAmount(String line) {
		return line.substring(line.indexOf("[") + 1, line.indexOf("]"));
	}

	private static String getCode(String line) {
		return line.substring(0, line.indexOf("("));
	}

}
