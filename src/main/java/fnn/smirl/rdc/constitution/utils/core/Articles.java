package fnn.smirl.rdc.constitution.utils.core;
import java.util.ArrayList;
import java.util.Collections;

public class Articles {
 public long versionId = 0;
 public ArrayList<Article> list = new ArrayList<Article>();

 public ArrayList<Long> getTitres() {
	ArrayList<Long> l = new ArrayList<>();
	l.add(new Long(0));
	for (Article a : list)l.add(a.titre.id);
	purge(l);
	Collections.sort(l);
	return l;
 }

 public ArrayList<Long> getChapitres(long titreId) {
	ArrayList<Long> l = new ArrayList<>();
	l.add(new Long(0));
	if (titreId == 0) {
	 for (Article a : list) {
		l.add(a.chapitre.id);
	 }
	} else {
	 for (Article a : list) {
		if (a.titre.id == titreId)l.add(a.chapitre.id);
	 }
	}
	purge(l);
	Collections.sort(l);
	return l;
 }

 public ArrayList<Long> getSections(long titreId, long chapId) {
	ArrayList<Long> l = new ArrayList<>();
	l.add(new Long(0));
	if (titreId == 0 && chapId == 0) {
	 for (Article a : list) {
		l.add(a.section.id);
	 }
	} else if (titreId != 0 && chapId == 0) {
	 for (Article a : list) {
		if (a.titre.id == titreId)l.add(a.section.id);
	 }
	} else if (titreId == 0 && chapId != 0) {
	 for (Article a : list) {
		if (a.chapitre.id == chapId)l.add(a.section.id);
	 }
	} else {
	 for (Article a : list) {
		if (a.titre.id == titreId && a.chapitre.id == chapId)l.add(a.section.id);
	 }
	}
	purge(l);
	Collections.sort(l);
	return l;
 }

 public ArrayList<Article> getArticles(long titreId, long chapId, long sectId) {
	ArrayList<Article> l = new ArrayList<>();
	if (titreId == 0 && chapId == 0 && sectId == 0) {
	 for (Article a : list) {
		l.add(a);
	 }
	} else if (titreId == 0 && chapId == 0 && sectId != 0) {
	 for (Article a : list) {
		if (a.section.id == sectId)l.add(a);
	 }
	} else if (titreId == 0 && chapId != 0 && sectId != 0) {
	 for (Article a : list) {
		if (a.chapitre.id == chapId && a.section.id == sectId)l.add(a);
	 }
	} else if (titreId != 0 && chapId == 0 && sectId != 0) {
	 for (Article a : list) {
		if (a.titre.id == titreId && a.section.id == sectId)l.add(a);
	 }
	} else if (titreId == 0 && chapId != 0 && sectId == 0) {
	 for (Article a : list) {
		if (a.chapitre.id == chapId)l.add(a);
	 }
	} else if (titreId != 0 && chapId != 0 && sectId == 0) {
	 for (Article a : list) {
		if (a.titre.id == titreId && a.chapitre.id == chapId)l.add(a);
	 }
	} else if (titreId != 0 && chapId == 0 && sectId == 0) {
	 for (Article a : list) {
		if (a.titre.id == titreId)l.add(a);
	 }
	} else {
	 for (Article a : list) {
		if (a.titre.id == titreId && a.chapitre.id == chapId && a.section.id == sectId)l.add(a);
	 }
	}
	Collections.sort(l);
	return l;
 }

 private<L> void purge(ArrayList<L> list) {
	ArrayList<L> temp = new ArrayList<>();
	for (L l : list) {
	 if (!temp.contains(l))temp.add(l);
	}
	list.clear();
	list.addAll(temp);
 }
 
 public ArrayList<Article> getBookmarked(){
	ArrayList<Article> l = new ArrayList<>();
	for(Article a : list){
	 if(a.bookmark)l.add(a);
	}
	Collections.sort(l);
	return l;
 }
 
 public ArrayList<Article> getArticlesThatContain(String expression){
	ArrayList<Article> l = new ArrayList<>();
	for(Article a : list){
	 if(a.contains(expression))l.add(a);
	}
	Collections.sort(l);
 return l;
}
}
