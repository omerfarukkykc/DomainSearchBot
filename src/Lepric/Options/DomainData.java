package Lepric.Options;
public class DomainData {
    String link;
    boolean status;
    int index;
    DomainData(String link,boolean status){
        this.link=link;
        this.status=status;
        
    }
    DomainData(String link,boolean status,int index){
        this.link=link;
        this.status=status;
        this.index=index;
    }
    DomainData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setDomainData(String link,boolean status){
        this.link=link;
        this.status=status;
    }
    public int getindex(){
        return this.index;
    }
    public String getDomainLink(){
        return this.link;
    }
    public boolean getStatus(){
        return this.status;
    }

}
