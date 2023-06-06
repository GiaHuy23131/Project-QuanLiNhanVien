
import QuanLyNhanVien.LoaiNV;
import QuanLyNhanVien.LoaiNhanVienModel;
import java.text.ParseException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jungp
 */
public class TestLoaiNV {
    @Test
    public void testAdd() throws ParseException {                      
        LoaiNV nv = new LoaiNV("NV07", "Huy",3);

        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.add(nv);

        boolean actual = nvModel.add(nv);

        LoaiNhanVienModel nvModel2 = new LoaiNhanVienModel();
        boolean expected = nvModel2.add(nv);

        assertEquals(expected, actual);

    }

    @Test
    public void testDelete() throws ParseException {
        LoaiNV nv = new LoaiNV("NV07", "Huy",3);  
        
        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.delete(nv.getMaLoai());

        boolean actual = nvModel.delete(nv.getMaLoai());

        LoaiNhanVienModel nvModel2 = new LoaiNhanVienModel();
        boolean expected = nvModel2.delete(nv.getMaLoai());

        assertEquals(expected, actual);

    }
    @Test
    public void testUpdate() throws ParseException {
        LoaiNV nv = new LoaiNV("NV07", "Huy",3);

        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.update(nv);

        boolean actual = nvModel.update(nv);

        LoaiNhanVienModel nvModel2 = new LoaiNhanVienModel();
        boolean expected = nvModel2.update(nv);

        assertEquals(expected, actual);

    }
    @Test
    public void testFind() throws ParseException {
        LoaiNV nv = new LoaiNV("NV07", "Huy",3);
        
        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.find(nv.getTenLoai());

        ArrayList<LoaiNV> array = nvModel.find(nv.getTenLoai());
        
        ArrayList<LoaiNV> arrayNV = new ArrayList<LoaiNV>();
        arrayNV = nvModel.find(nv.getTenLoai());

        assertEquals(array, arrayNV);

    }
    @Test
    public void testSortTen() throws ParseException {
        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.sapXepLoaiNVTheoTen();

        ArrayList<LoaiNV> array = nvModel.sapXepLoaiNVTheoTen();
        
        ArrayList<LoaiNV> arrayNV = new ArrayList<LoaiNV>();
        arrayNV = nvModel.sapXepLoaiNVTheoTen();

        assertEquals(array, arrayNV);

    }
    @Test
    public void testSortLuongVaTen() throws ParseException {
        LoaiNhanVienModel nvModel = new LoaiNhanVienModel();
        nvModel.sapXepLoaiNVTheoSoLuongVaTen();

        ArrayList<LoaiNV> array = nvModel.sapXepLoaiNVTheoSoLuongVaTen();
        
        ArrayList<LoaiNV> arrayNV = new ArrayList<LoaiNV>();
        arrayNV = nvModel.sapXepLoaiNVTheoSoLuongVaTen();

        assertEquals(array, arrayNV);

    }
}
