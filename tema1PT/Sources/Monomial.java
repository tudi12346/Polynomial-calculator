package com.assignment1.model;

import com.assignment1.model.Monomial;

public class Monomial implements Comparable<Monomial> {
		private Double coefficient;
		private Double grade;

		public Double getCoefficient() {
			return coefficient;
		}

		public void setCoefficient(Double coefficient) {
			this.coefficient = coefficient;
		}

		public Double getGrade() {
			return grade;
		}

		public void setGrade(Double grade) {
			this.grade = grade;
		}

		

		public static Monomial retMon(Double coef,Double grade) {//returneaza un nou monom care are gradul si coeficientul specificate
			Monomial aux=new Monomial();
			aux.setCoefficient(coef);
			aux.setGrade(grade);
			return aux;
		}
		
		@Override
		public int compareTo(Monomial o) {//a fost implementata pt a putea apela Collections.sort
			return   (int) (o.getGrade()-this.getGrade());
		}
		
		

	}

