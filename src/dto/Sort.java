package dto;

/* ソート時に使用 保持する型はint
 * 呼び出し元が数字でソート内容を認識するため
 * */
public class Sort {
	private int flg;

	public Sort(int flg){
		this.flg = flg;
	}

	public int getFlg(){
		return flg;
	}

	public void setFlg(int flg){
		this.flg = flg;
	}
}