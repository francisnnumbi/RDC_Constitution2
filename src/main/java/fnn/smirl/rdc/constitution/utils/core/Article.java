package fnn.smirl.rdc.constitution.utils.core;
import fnn.smirl.rdc.constitution.utils.Harmony;
import fnn.smirl.rdc.constitution.MaConstitution;

public class Article implements Comparable<Article> {
 public long id = 1;
 public String content = "";
 public Titre titre = new Titre();
 public Chapitre chapitre = new Chapitre();
 public Section section = new Section();
 public boolean bookmark = false;
 public String note = "";

 @Override
 public String toString() {
	// TODO: Implement this method
	return "Article " + id;
 }

 public boolean contains(String expression) {
	StringBuffer sb = new StringBuffer();
	sb.append(titre.title + "\n");
	sb.append(chapitre.title + "\n");
	sb.append(section.title + "\n");
	sb.append(content);
	expression.toLowerCase();
	String s = Harmony.harmonize(MaConstitution.APPLICATION_CONTEXT, sb.toString().toLowerCase());
	return s.contains(Harmony.harmonize(MaConstitution.APPLICATION_CONTEXT, expression.toLowerCase()));
 }

 public String toDetailedString() {
	StringBuffer sb = new StringBuffer();
	sb.append("Titre " + titre.id + ": " + titre.title + "\n");
	if (chapitre != null)sb.append("Chapitre " + chapitre.id + ": " + chapitre.title + "\n");
	if (section != null)	sb.append("Section " + section.id + ": " + section.title + "\n\n");
	sb.append("Article " + id + ":\n" + content);
	return sb.toString();
 }

 @Override
 public int compareTo(Article p1) {
	// TODO: Implement this method
	return Long.compare(id, p1.id);
 }

 @Override
 public boolean equals(Object o) {
	// TODO: Implement this method
	return Long.compare(id, ((Article)o).id) == 1;
 }

}
