/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.mycompany.DBConext.HibernatUtil;

/**
 *
 * @author ktkha
 */
public class ThemNhanhSPRepository {
    
    public int getMaxMa() { //Lấy mã lớn nhất + 1 => Tự động tăng
        Session session = HibernatUtil.getFACTORY().openSession();
        String soMaLonNhat = null;
        Query q = session.createQuery(" select A.Ma From SanPham A Where TrangThai = 1 ");
        List<String> i = q.getResultList(); //Lay list String 
        if (i.isEmpty()) {
            return 0;
        } else {
            List<Integer> c = new ArrayList<>(); //Convert list String sang List Integer
            for (String a : i) {
                c.add(Integer.parseInt(a));
            }
            System.out.println(c + "\n");

            int max = c.get(0); //Từ list int lấy ra số lớn nhất
            for (int j = 0; j < c.size(); j++) {
                if (c.get(j).compareTo(max) > 0) {
                    max = c.get(j);
                }
            }

            return max;
        }
    }

    public boolean add(SanPham s) {
        String getMa = String.valueOf(getMaxMa() + 1);

        try {
            Session session = HibernatUtil.getFACTORY().openSession();

            SanPham sp = new SanPham();
            sp.setMa(getMa);
            sp.setTen(s.getTen());
            sp.setTrangThai(1);

            session.getTransaction().begin();
            session.save(sp);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    
    
}
