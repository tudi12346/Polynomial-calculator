package com.assignment1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment1.model.Monomial;
import com.assignment1.model.Polynomial;

public class PolynomialController {

	public static Polynomial add(Polynomial a, Polynomial b) {// aduna cele 2 polinoame;
		HashMap<Double, Double> terms = new HashMap<Double, Double>();// se creeaz un polinom nou la care se adauga toti
																		// termenii celor
		Polynomial result = new Polynomial();// doua polinoame a si b
		result.setTerms(terms);// acestia sunt adaugati folosind functia addTerm din clasa Polinom
		Double key, value;
		for (Map.Entry<Double, Double> mapElement : a.getTerms().entrySet()) {
			key = (Double) mapElement.getKey();
			value = (Double) mapElement.getValue();
			result.addTerm(value, key);
		}
		for (HashMap.Entry<Double, Double> mapElement : b.getTerms().entrySet()) {
			key = (Double) mapElement.getKey();
			value = ((Double) mapElement.getValue());
			// System.out.println(value);
			result.addTerm(value, key);
		}
		return result;

	}

	public static Polynomial sub(Polynomial n, Polynomial s) {// exact ca adunarea, doar ca la polinomul n se aduna
																// polinomul s*(-1)
		HashMap<Double, Double> terms = new HashMap<Double, Double>();// inmultirea coeficientilor lui s cu -1 se face
																		// inainte
		Polynomial result = new Polynomial();// sa fie adaugati la rezultat
		result.setTerms(terms);
		Double key, value;
		for (HashMap.Entry<Double, Double> mapElement : n.getTerms().entrySet()) {
			key = (Double) mapElement.getKey();
			value = ((Double) mapElement.getValue());
			result.addTerm(value, key);
		}
		for (HashMap.Entry<Double, Double> mapElement : s.getTerms().entrySet()) {
			key = (Double) mapElement.getKey();
			value = ((Double) mapElement.getValue()) * (-1);
			result.addTerm(value, key);
		}
		return result;
	}

	public static void print(Polynomial p) {// prints the raw form of the polynomial, with coefficients=0 and so on....
		for (HashMap.Entry<Double, Double> mapElement : p.getTerms().entrySet()) {
			Double key = (Double) mapElement.getKey();// it is not used in this project so it can be deleted
			Double value = ((Double) mapElement.getValue());// was implemented for verification purposes only
			System.out.print(value + "x^" + key + " ");
		}
		System.out.println();
	}

	public static void prettyPrint(Polynomial p) {// afiseaza polinomul in forma cunoscuta de la matematica :)))))
		ArrayList<Monomial> terms = new ArrayList<Monomial>();// termenii sunt afisati in ordinea descrescatoare a
																// gradelor
		p.refactor();
		for (HashMap.Entry<Double, Double> mapElement : p.getTerms().entrySet()) {
			Double key = (Double) mapElement.getKey();
			Double value = ((Double) mapElement.getValue());
			terms.add(Monomial.retMon(value, key));
		}
		Collections.sort(terms);
		for (Monomial i : terms) {
			System.out.print(i.getCoefficient() + "x^" + i.getGrade() + " ");
		}
		System.out.println();
	}

	public static String retText(Polynomial p) {// transforma polinomul in String pt a fi afisat de GUI
		ArrayList<Monomial> terms = new ArrayList<Monomial>();
		p.refactor();
		for (HashMap.Entry<Double, Double> mapElement : p.getTerms().entrySet()) {
			Double key = (Double) mapElement.getKey();
			Double value = ((Double) mapElement.getValue());
			terms.add(Monomial.retMon(value, key));
		}
		String res = "";
		Collections.sort(terms);
		for (Monomial i : terms) {
			res += i.getCoefficient() + "x^" + i.getGrade() + "   ";
		}
		return res;

	}

	public static Polynomial mtp(Polynomial a, Polynomial b) {// inmulteste cele doua polinoame
		HashMap<Double, Double> terms = new HashMap<Double, Double>();// se ia fiecare termen din a , se inmulteste pe
																		// rand cu termenii
		Polynomial result = new Polynomial();// din b si rezultatele sunt adaugate la noul polinom cu funtia AddTerm
		result.setTerms(terms);// care nu creeaza duplicate
		Double key, value;
		Iterator<Map.Entry<Double, Double>> i = a.getTerms().entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<Double, Double> ei = i.next();
			Iterator<Map.Entry<Double, Double>> j = b.getTerms().entrySet().iterator();
			while (j.hasNext()) {
				Map.Entry<Double, Double> ej = j.next();

				key = (Double) (ei.getKey()) + (Double) (ej.getKey());
				value = (Double) (ei.getValue()) * (Double) (ej.getValue());
				result.addTerm(value, key);
			}
		}
		return result;
	}

	public static Polynomial div(Polynomial n, Polynomial s) {
		HashMap<Double, Double> terms = new HashMap<Double, Double>();
		Polynomial result = new Polynomial();
		result.setTerms(terms);
		HashMap<Double, Double> nct = new HashMap<Double, Double>();
		Polynomial nc = new Polynomial();
		nct.putAll(n.getTerms());
		nc.setTerms(nct);//o copie a polinomului dat ca parametru
		Double coef = 0d;
		Double grade = 0d;
		nc.refactor();//se elimina zerourile din el
		Double maxSGrade = s.getMaxGrade().getGrade();//se ia monomul de grad maxim din s
		while (nc.getTerms().isEmpty() == false && nc.getMaxGrade().getGrade() >= maxSGrade) {//daca gradul lui n>gradu s 
			grade = nc.getMaxGrade().getGrade() - maxSGrade;//se face impartirea monoamelor
			coef = nc.getMaxGrade().getCoefficient() / s.getMaxGrade().getCoefficient();
			HashMap<Double, Double> list = new HashMap<Double, Double>();
			Polynomial interm = new Polynomial();
			interm.setTerms(list);
			interm.addTerm(coef, grade);//se pune monomul obtinut intr un polinom intermediar
			result.addTerm(coef, grade);//se adauga la rezultatul final de asemenea
			Polynomial interm2 = PolynomialController.mtp(s, interm);//inmultim monomul cu impartitorul si apoi se scade rezultatul din
			nc = PolynomialController.sub(nc, interm2);//deimpartit
			nc.refactor();//se elimina zerourile din deimpartit
		}
		
		return result;
	}
	
	public static Polynomial mod(Polynomial n, Polynomial s) {//idem cu impartirea doar ca se salveaza restul
		HashMap<Double, Double> terms = new HashMap<Double, Double>();
		Polynomial result = new Polynomial();
		
		HashMap<Double, Double> nct = new HashMap<Double, Double>();
		Polynomial nc = new Polynomial();
		nct.putAll(n.getTerms());
		nc.setTerms(nct);
		Double coef = 0d;
		Double grade = 0d;
		nc.refactor();
		Double maxSGrade = s.getMaxGrade().getGrade();
		while (nc.getTerms().isEmpty() == false && nc.getMaxGrade().getGrade() >= maxSGrade) {
			grade = nc.getMaxGrade().getGrade() - maxSGrade;
			coef = nc.getMaxGrade().getCoefficient() / s.getMaxGrade().getCoefficient();
			HashMap<Double, Double> list = new HashMap<Double, Double>();
			Polynomial interm = new Polynomial();
			interm.setTerms(list);
			interm.addTerm(coef, grade);
			Polynomial interm2 = PolynomialController.mtp(s, interm);
			nc = PolynomialController.sub(nc, interm2);
			nc.refactor();
		}
		PolynomialController.prettyPrint(nc);
		terms.putAll(nc.getTerms());
		result.setTerms(terms);
		return result;
	}

	public static Polynomial dvt(Polynomial a) {// se deriveaza polinomul, termen cu termen
		HashMap<Double, Double> terms = new HashMap<Double, Double>();
		Polynomial result = new Polynomial();
		result.setTerms(terms);
		Double value, key;
		Iterator<Map.Entry<Double, Double>> i = a.getTerms().entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<Double, Double> entry = i.next();
			if (entry.getKey() == 0) {
				i.remove();
			} else {
				if (entry.getValue() == 0) {
					i.remove();
				} else {
					value = entry.getValue() * entry.getKey();//se inmulteste coef cu gradul
					key = entry.getKey() - 1;//se scade 1 din grad
					result.addTerm(value, key);
				}
			}
		}
		return result;
	}

	public static Polynomial itg(Polynomial a) {// se integreaza polinomul termen cu termen
		HashMap<Double, Double> terms = new HashMap<Double, Double>();// conventia este ca acea constanta care apare la
																		// integrare
		Polynomial result = new Polynomial();// va fi egala cu 0.1234
		result.setTerms(terms);
		Double value, key;
		Iterator<Map.Entry<Double, Double>> i = a.getTerms().entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<Double, Double> entry = i.next();
			if (entry.getValue() == 0) {
				i.remove();
			} else {
				key = entry.getKey() + 1;//se aduna 1 la grad
				value = entry.getValue() / key;//se imparte coef la grad+1

				result.addTerm(value, key);
			}
		}
		result.addTerm(0.1234d, 0d);
		return result;
	}

	public static Polynomial mkFromString(String poly) {// foloseste regex pt a identifica grupuri posibile la scrierea
														// polinomului
		HashMap<Double, Double> terms = new HashMap<Double, Double>();// aceste grupuri sunt parsate si intrduse
		Polynomial result = new Polynomial();// intr un polinom reprezentat prin obiectul Polynomial, pe care pot fi
												// efectuate operatiile
		result.setTerms(terms);// implementate mai sus
		Pattern pat = Pattern.compile("([+-]?\\d*)x(\\^(\\d+))?|([+-]?\\d+)");
		Matcher m = pat.matcher(poly);
		Double coef = 0d, grade = 0d;
		while (m.find()) {
			if (m.group(4) == null) {//daca termenul gasit nu e un numar simplu
				if (m.group(3) == null)//daca exponentul lipseste
					grade = 1d;//monomul are gradul 1
				else
					grade = Double.parseDouble(m.group(3));//altfel gradul este dat de grupul 3
				if (m.group(1).equals(""))//daca grupul1 e gol insemna ca avem o expre de forma x^ceva, deci coef va fi 1
					coef = 1d;
				else {
					if (m.group(1).equals("-"))//daca expresia e de forma -x^ceva
						coef = -1d;//coef va fi -1
					else if (m.group(1).equals("+"))
						coef = 1d;//idem pt +x^ceva
					else
						coef = Double.parseDouble(m.group(1));//altfel coef e dat de grupul 1 
				}
			} else {
				coef = Double.parseDouble(m.group(4));//grupul 4 va fi nenul doar daca avem expr de forma +-constanta
				grade = 0d;
			}
			result.addTerm(coef, grade);
		}
		return result;
	}

}
