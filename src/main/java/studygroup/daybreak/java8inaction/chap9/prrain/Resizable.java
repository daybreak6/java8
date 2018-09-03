package studygroup.daybreak.java8inaction.chap9.prrain;

public interface Resizable extends Drawable{
    public int getWidth();
    public int getHeight();
    public void setWidth(int width);
    public void setHeight(int height);
    public void setAbsoluteSize(int width, int height);
    
    //public void setRelativeSize(int wFactor, int hFactor);
    //추가되면 재컴파일시 에러 발생 
    //Resizable을 구현하는 모든 클래스는 setRelativeSize 메서를 구현. 
    
    //디폴트 메서드 :  defualt 키워드로 시작하며, 다른클래스에 선언된 메서드처럼 메서드 바디를 포함한다. 
    // 인터페이스에 디폴트 메서드를 추가하면 소스 호환성이 유지. 
    default void setRelativeSize(int wFactor, int hFactor) {
    	setAbsoluteSize(getWidth()/ wFactor , getHeight()/ hFactor);
    }
}


