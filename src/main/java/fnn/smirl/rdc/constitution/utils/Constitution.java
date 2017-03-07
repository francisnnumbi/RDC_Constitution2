package fnn.smirl.rdc.constitution.utils;

public class Constitution
{
	
	private String titre, chapter, section, paragraphe, article, text;
	private boolean bookmark = false;
	
	public Constitution(){
		
	}
	public Constitution(String article, String text){
	 this("","","","",article,text);
	}
	
	public Constitution(String titre, String chapter, String section, String paragraphe, String article, String text){
		this.titre = titre;
		this.chapter = chapter;
		this.section = section;
		this.paragraphe = paragraphe;
		this.article = article;
		this.text = text;
		
		}
		
		public boolean isBookmarked(){
			return bookmark;
		}
		
		public void setBookmark(boolean bookmark){
			this.bookmark = bookmark;
		}

	@Override
	public String toString()
		{
			// TODO: Implement this method
			return this.article;
		}
		

	public String getArticle()
		{
			return article;
		}

	public String getText()
		{
			return text;
		}
		
		public String getTitre()
			{
				return titre;
			}
		
		public String getChapter()
			{
				return chapter;
			}
			
		public String getSection()
			{
				return section;
			}
			
			public String getParagraphe(){
				return paragraphe;
			}
			
		public String toDetailedString(){
			return 
				//titre + "\n" 
		//	+ chapter + "\n"
		//	+ section + "\n"
		//	+ paragraphe + "\n"
			article + "\n\n" 
			+ text;
		}
	
	
	
}
