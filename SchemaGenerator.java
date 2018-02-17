package com.xo.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Sahu
 *
 */
public class SchemaGenerator {
	
	private static BufferedWriter bout;
	public static void main(String[] args) throws Exception {
		String inputFile = args[0];
		String outputFile= args[1];
		BufferedReader yoyo = new BufferedReader(new FileReader(inputFile));
		bout = new BufferedWriter(new FileWriter(outputFile));
		if (inputFile.endsWith(".csv")) {
			String result = csvSchemagenerator(yoyo);
			System.out.println(result);
			bout.write(result);
			bout.flush();
			yoyo.close();
		} else if (inputFile.endsWith(".tsv")) {
			String result = tsvSchemagenerator(yoyo);
			System.out.println(result);
			bout.write(result);
			bout.flush();
			yoyo.close();
		}
	}

	public static String csvSchemagenerator(BufferedReader yoyo) throws Exception {

		String u = yoyo.readLine();
		String startSchema = "{ \"type\" : \"record\",\n \"namespace\" : \"Tutorialspoint\",\n \"name\" : \"Employee\",\n \"fields\" : ";
		String endSchema = "}";
		String[] e = u.split(",");
		List<Object> y = new ArrayList<>();
		for (String string : e) {
			String tempfield = "\n{ \"name\" : \"" + string + "\" , \"type\" : \"string\" }";
			y.add(tempfield);
		}

		String finalschema = startSchema + "\n" + y.toString() + "\n" + endSchema;
		return finalschema;
	}
	public static String tsvSchemagenerator(BufferedReader yoyo) throws Exception {

		String u = yoyo.readLine();
		String startSchema = "{ \"type\" : \"record\",\n \"namespace\" : \"Tutorialspoint\",\n \"name\" : \"Employee\",\n \"fields\" : ";
		String endSchema = "}";
		String[] e = u.split("\t");
		List<Object> y = new ArrayList<>();
		for (String string : e) {
			String tempfield = "\n{ \"name\" : \"" + string + "\" , \"type\" : \"string\" }";
			y.add(tempfield);
		}

		String finalschema = startSchema + "\n" + y.toString() + "\n" + endSchema;
		return finalschema;
	}
}
