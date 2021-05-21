package jmp.spring.vo;

import lombok.Data;

@Data
public class Menu {
	public int level;
	public String menu_id;
	public String title;
	public String url;
	public String up_menu_id;
}

    /*String MENU_ID; 
	String UP_MENU_ID; 
    String TITLE; 
	String URL; 
	int SORT; 
	char VISIBLE; 
}
*/