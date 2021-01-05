package com.assignment1.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Polynomial {
	private HashMap<Double , Double> terms;//polinomul este de fapt un hash map in care sunt stocate perechi<coeficient,grad>

	public HashMap<Double, Double> getTerms() {
		return terms;
	}

	public void setTerms(HashMap<Double, Double> terms) {
		this.terms = terms;
	}

	public Double addMonTerm(Monomial x) {//o functie care adauga in lista de termeni a polinomului un monom nou. 
		Double cf = terms.get(x.getGrade());//daca exista deja un termen de acelasi grad, functia va aduna coeficientii
		if (cf != null) {
			cf += x.getCoefficient();
			return terms.put(x.getGrade(), cf);
		} else {
			return terms.put(x.getGrade(), x.getCoefficient());
		}
	}
	
	public Double addTerm(Double coef,Double grade) {//o functie care adauga in lista de termeni a polinomului o pereche noua.
		Double cf = terms.get(grade);//daca exista o valoare asociata gradului, functia va aduna coeficientii
		if (cf != null) {
			cf += coef;
			return terms.put(grade, cf);
		} else {
			return terms.put(grade, coef);
		}
	}
	
	public void refactor() {//aceasta functie elimina elementele care au coeficientul 0 din polinom 	
		Double value;
		Iterator<Map.Entry<Double,Double>> i = terms.entrySet().iterator();
		while(i.hasNext())
			{
			Map.Entry<Double,Double> entry=i.next();
			
			value = (Double) entry.getValue();
			if (value==0)
			{
				i.remove();
			}
		}
	}
	
	public Monomial getMaxGrade() {//returneaza monomul cu grad maxim din polinom
		Monomial res=new Monomial();
		Double maxKey=Collections.max(this.getTerms().keySet());
		res.setCoefficient(this.getTerms().get(maxKey));
		res.setGrade(maxKey);
		return res;
		}
}
