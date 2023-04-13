package model;


import java.io.Serializable;

// EMPLOYEESテーブルのレコードを表すクラス
public class Employee implements Serializable {
	// テーブルに対するフィールドを定義
	private String id;
	private String name;
	private int age;
	
	public Employee() {
		
	}
	
	// インスタンス化したときにレコードの情報をセットできるコンストラクタを定義
	public Employee(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	// ゲッターを設定
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
	

}
