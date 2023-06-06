
import QuanLyNhanVien.NhanVien;
import QuanLyNhanVien.NhanVienModel;
import java.text.ParseException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
/**
 *
 * @author fff
 */
public class TestNhanVien {

    @Test
    public void testAdd() throws ParseException {                   //(2 diem)      
        NhanVien nv = new NhanVien("07", "Huy", "2001-10-25", "NV01", "Nam", "30", "30");

        NhanVienModel nvModel = new NhanVienModel();
        nvModel.add(nv);

        boolean actual = nvModel.add(nv);

        NhanVienModel nvModel2 = new NhanVienModel();
        boolean expected = nvModel2.add(nv);

        assertEquals(expected, actual);

    }

    @Test
    public void testDelete() throws ParseException {
        NhanVien nv = new NhanVien("07", "Huy", "2001-10-25", "NV01", "Nam", "30", "30");   
        
        NhanVienModel nvModel = new NhanVienModel();
        nvModel.delete(nv.getMaNV());

        boolean actual = nvModel.delete(nv.getMaNV());

        NhanVienModel nvModel2 = new NhanVienModel();
        boolean expected = nvModel2.delete(nv.getMaNV());

        assertEquals(expected, actual);

    }
    @Test
    public void testUpdate() throws ParseException {
        NhanVien nv = new NhanVien("07", "Huy", "2001-10-25", "NV01", "Nam", "30", "30");   
        
        NhanVienModel nvModel = new NhanVienModel();
        nvModel.update(nv);

        boolean actual = nvModel.update(nv);

        NhanVienModel nvModel2 = new NhanVienModel();
        boolean expected = nvModel2.update(nv);

        assertEquals(expected, actual);

    }
    @Test
    public void testFind() throws ParseException {
        NhanVien nv = new NhanVien("07", "Huy", "2001-10-25", "NV01", "Nam", "30", "30");   
        
        NhanVienModel nvModel = new NhanVienModel();
        nvModel.find(nv.getMaNV());

        ArrayList<NhanVien> array = nvModel.find(nv.getMaNV());
        
        ArrayList<NhanVien> arrayNV = new ArrayList<NhanVien>();
        arrayNV = nvModel.find(nv.getMaNV());

        assertEquals(array, arrayNV);

    }
    @Test
    public void testSortTen() throws ParseException {
        NhanVienModel nvModel = new NhanVienModel();
        nvModel.sapXepTheoTen();

        ArrayList<NhanVien> array = nvModel.sapXepTheoTen();
        
        ArrayList<NhanVien> arrayNV = new ArrayList<NhanVien>();
        arrayNV = nvModel.sapXepTheoTen();

        assertEquals(array, arrayNV);

    }
    @Test
    public void testSortLuongVaTen() throws ParseException {
        NhanVienModel nvModel = new NhanVienModel();
        nvModel.sapXepTheoLuongVaTen();

        ArrayList<NhanVien> array = nvModel.sapXepTheoLuongVaTen();
        
        ArrayList<NhanVien> arrayNV = new ArrayList<NhanVien>();
        arrayNV = nvModel.sapXepTheoLuongVaTen();

        assertEquals(array, arrayNV);

    }

}
