package ucl.comp0022.team2.model;

import java.util.ArrayList;

public class DaoParam {
    private ArrayList<Integer> selectEnum = new ArrayList<>();
    private ArrayList<String> selectValue = new ArrayList<>();
    private ArrayList<Integer> sortEnum = new ArrayList<>();
    private ArrayList<Boolean> sortBoolean = new ArrayList<>();
    private String limit;

    public DaoParam(String[] selectParams, String[] sortParams) {
        if(selectParams[0].equals("") && selectParams[1].equals("") && selectParams[2].equals("") && selectParams[3].equals("")){
            selectEnum.add(0);
        } else {
            for(int i = 0; i < selectParams.length; i++) {
                if(!selectParams[i].equals("")){
                    selectEnum.add(i+1);
                    selectValue.add(selectParams[i]);
                }
            }
        }
        if(!sortParams[0].equals("")){
            switch (sortParams[0]){
                case "title":
                    sortEnum.add(1);
                    break;
                case "rating":
                    sortEnum.add(2);
                    break;
                case "year":
                    sortEnum.add(3);
                    break;
            }
        } else {
            sortEnum.add(0);
        }
        if(sortParams[1].equals("desc")){
            sortBoolean.add(false);
        } else {
            sortBoolean.add(true);
        }
        limit = sortParams[2];
    }

    public ArrayList<Integer> getSelectEnum() {
        return selectEnum;
    }

    public void setSelectEnum(ArrayList<Integer> selectEnum) {
        this.selectEnum = selectEnum;
    }

    public ArrayList<String> getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(ArrayList<String> selectValue) {
        this.selectValue = selectValue;
    }

    public ArrayList<Integer> getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(ArrayList<Integer> sortEnum) {
        this.sortEnum = sortEnum;
    }

    public ArrayList<Boolean> getSortBoolean() {
        return sortBoolean;
    }

    public void setSortBoolean(ArrayList<Boolean> sortBoolean) {
        this.sortBoolean = sortBoolean;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
