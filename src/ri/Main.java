/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ri;

/**
 *
 * @author strokyl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PostrGreFactory fact = new PostrGreFactory();
        PostGreConnection post = fact.getConnect();
    }

}
