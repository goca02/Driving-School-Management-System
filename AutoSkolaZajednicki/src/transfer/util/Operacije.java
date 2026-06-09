/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Goca
 */
public interface Operacije {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_UCENIK = 2;
    public static final int DELETE_UCENIK = 3;
    public static final int UPDATE_UCENIK = 4;
    public static final int GET_ALL_UCENIK = 5;

    public static final int ADD_PLAN_CASA = 6;
    public static final int DELETE_PLAN_CASA = 7;
    public static final int UPDATE_PLAN_CASA = 8;
    public static final int GET_ALL_PLAN_CASA = 9;

    public static final int ADD_EVIDENCIJA_VOZNJE = 10;
    public static final int DELETE_EVIDENCIJA_VOZNJE = 11;
    public static final int UPDATE_EVIDENCIJA_VOZNJE = 12;
    public static final int GET_ALL_EVIDENCIJA_VOZNJE = 13;

    public static final int GET_ALL_STAVKA_EVIDENCIJE = 14;

    public static final int GET_ALL_KATEGORIJA = 15;

}
