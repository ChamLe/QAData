/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassifyv1.pkg1;

/**
 *
 * @author tutyb_000
 */
public class Convert {

    public static String convertFromConsortiumToNormal(String s) {
        Character[] chars = new Character[]{
            'a', 'ă', 'â', 'e', 'ê', 'i',
            'o', 'ô', 'ơ', 'u', 'ư', 'y'
        };

        Character thanhs[] = new Character[]{
            769, 768, 777, 771, 803
        };

        String[][] replacement = new String['ư' + 1][804];

        replacement['a'][769] = "á";
        replacement['a'][768] = "à";
        replacement['a'][777] = "ả";
        replacement['a'][771] = "ã";
        replacement['a'][803] = "ạ";

        replacement['A'][769] = "Á";
        replacement['A'][768] = "À";
        replacement['A'][777] = "Ả";
        replacement['A'][771] = "Ã";
        replacement['A'][803] = "Ạ";

        replacement['ă'][769] = "ắ";
        replacement['ă'][768] = "ằ";
        replacement['ă'][777] = "ẳ";
        replacement['ă'][771] = "ẵ";
        replacement['ă'][803] = "ặ";

        replacement['Ă'][769] = "Ắ";
        replacement['Ă'][768] = "Ằ";
        replacement['Ă'][777] = "Ẳ";
        replacement['Ă'][771] = "Ẵ";
        replacement['Ă'][803] = "Ặ";

        replacement['â'][769] = "ấ";
        replacement['â'][768] = "ầ";
        replacement['â'][777] = "ẩ";
        replacement['â'][771] = "ẫ";
        replacement['â'][803] = "ậ";

        replacement['Â'][769] = "Ấ";
        replacement['Â'][768] = "Ầ";
        replacement['Â'][777] = "Ẩ";
        replacement['Â'][771] = "Ẫ";
        replacement['Â'][803] = "Ậ";

        replacement['e'][769] = "é";
        replacement['e'][768] = "è";
        replacement['e'][777] = "ẻ";
        replacement['e'][771] = "ẽ";
        replacement['e'][803] = "ẹ";
        
        replacement['E'][769] = "É";
        replacement['E'][768] = "È";
        replacement['E'][777] = "Ẻ";
        replacement['E'][771] = "Ẽ";
        replacement['E'][803] = "Ẹ";

        replacement['ê'][769] = "ế";
        replacement['ê'][768] = "ề";
        replacement['ê'][777] = "ể";
        replacement['ê'][771] = "ễ";
        replacement['ê'][803] = "ệ";

        replacement['Ê'][769] = "Ế";
        replacement['Ê'][768] = "Ề";
        replacement['Ê'][777] = "Ể";
        replacement['Ê'][771] = "Ễ";
        replacement['Ê'][803] = "Ệ";

        replacement['i'][769] = "í";
        replacement['i'][768] = "ì";
        replacement['i'][777] = "ỉ";
        replacement['i'][771] = "ĩ";
        replacement['i'][803] = "ị";

        replacement['I'][769] = "Í";
        replacement['I'][768] = "Ì";
        replacement['I'][777] = "Ỉ";
        replacement['I'][771] = "Ĩ";
        replacement['I'][803] = "Ị";

        replacement['o'][769] = "ó";
        replacement['o'][768] = "ò";
        replacement['o'][777] = "ỏ";
        replacement['o'][771] = "õ";
        replacement['o'][803] = "ọ";

        replacement['O'][769] = "Ó";
        replacement['O'][768] = "Ò";
        replacement['O'][777] = "Ỏ";
        replacement['O'][771] = "Õ";
        replacement['O'][803] = "Ọ";

        replacement['ô'][769] = "ố";
        replacement['ô'][768] = "ồ";
        replacement['ô'][777] = "ổ";
        replacement['ô'][771] = "ỗ";
        replacement['ô'][803] = "ộ";

        replacement['Ô'][769] = "Ố";
        replacement['Ô'][768] = "Ồ";
        replacement['Ô'][777] = "Ổ";
        replacement['Ô'][771] = "Ỗ";
        replacement['Ô'][803] = "Ộ";

        replacement['ơ'][769] = "ớ";
        replacement['ơ'][768] = "ờ";
        replacement['ơ'][777] = "ở";
        replacement['ơ'][771] = "ỡ";
        replacement['ơ'][803] = "ợ";

        replacement['Ơ'][769] = "Ớ";
        replacement['Ơ'][768] = "Ờ";
        replacement['Ơ'][777] = "Ở";
        replacement['Ơ'][771] = "Ỡ";
        replacement['Ơ'][803] = "Ợ";

        replacement['u'][769] = "ú";
        replacement['u'][768] = "ù";
        replacement['u'][777] = "ủ";
        replacement['u'][771] = "ũ";
        replacement['u'][803] = "ụ";

        replacement['U'][769] = "Ú";
        replacement['U'][768] = "Ù";
        replacement['U'][777] = "Ủ";
        replacement['U'][771] = "Ũ";
        replacement['U'][803] = "Ụ";

        replacement['ư'][769] = "ứ";
        replacement['ư'][768] = "ừ";
        replacement['ư'][777] = "ử";
        replacement['ư'][771] = "ữ";
        replacement['ư'][803] = "ự";

        replacement['Ư'][769] = "Ứ";
        replacement['Ư'][768] = "Ừ";
        replacement['Ư'][777] = "Ử";
        replacement['Ư'][771] = "Ữ";
        replacement['Ư'][803] = "Ự";

        replacement['y'][769] = "ý";
        replacement['y'][768] = "ỳ";
        replacement['y'][777] = "ỷ";
        replacement['y'][771] = "ỹ";
        replacement['y'][803] = "ỵ";

        replacement['Y'][769] = "Ý";
        replacement['Y'][768] = "Ỳ";
        replacement['Y'][777] = "Ỷ";
        replacement['Y'][771] = "Ỹ";
        replacement['Y'][803] = "Ỵ";

        for (Character cha : chars) {
            for (Character thanh : thanhs) {
                s = s.replace(cha.toString() + thanh.toString(), replacement[(int) cha][(int) thanh]);
            }
        }

        return s;
    }
}
