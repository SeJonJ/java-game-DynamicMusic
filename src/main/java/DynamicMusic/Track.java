package DynamicMusic;

public class Track {

   /* 트랙 클래스는 하나의 곡에 대한 정보를 담는 클래스.
   즉, 하나의 곡에 대한 표지, 게임 선택 화면에서의 음악, 게임 실행 시 음악
    */
    private String titleImage; // 제목 이미지
    private String menuImage; // 게임 선택 창 표지 이미지
    private String ingameImage; // 해당 곡을 실행했을 때 표지 이미지
    private String selectMusic; // 곡을 선택 했을 때 이미지
    private String gameMusic; // 해당 곡을 실행햇을 때 음악
    private String titleName; // 해당 곡 제목 확인
    private String diffiCulity; // 해당 곡 난이도 확인
    private int musicTime; // 음악 종료 시간


    public Track(String titleImage, String menuImage, String ingameImage, String selectMusic, String gameMusic, String titleName, int musicTime){
        super(); // 아무것도 상속받지 않은 클래스의 super 클래스 즉 상위 클래스는 Object 클래스가 됨
        this.titleImage = titleImage;
        this.menuImage = menuImage;
        this.ingameImage = ingameImage;
        this.selectMusic = selectMusic;
        this.gameMusic = gameMusic;
        this.titleName = titleName;
        this.musicTime = musicTime;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public String getIngameImage() {
        return ingameImage;
    }

    public void setIngameImage(String ingameImage) {
        this.ingameImage = ingameImage;
    }

    public String getSelectMusic() {
        return selectMusic;
    }

    public void setSelectMusic(String selectMusic) {
        this.selectMusic = selectMusic;
    }

    public String getGameMusic(){
        return gameMusic;
    }
    public void setGameMusic(String gameMusic){
        this.gameMusic = gameMusic;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(int musicTime) {
        this.musicTime = musicTime;
    }
}
