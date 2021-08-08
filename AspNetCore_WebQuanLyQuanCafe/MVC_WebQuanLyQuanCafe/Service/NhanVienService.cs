using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MVC_WebQuanLyQuanCafe.Models;

namespace MVC_WebQuanLyQuanCafe.Service
{
    public class NhanVienService
    {
        dbQLCFDataContext db = new dbQLCFDataContext();
        public NHANVIEN KiemTraDangNhap(string TenTaiKhoan, string MatKhau)
        {
            try
            {
                NHANVIEN nhanVien = db.NHANVIENs.Single(nv => nv.TENTAIKHOAN == TenTaiKhoan && nv.MATKHAU == MatKhau
                                                    && nv.CHUCVU.Equals("Quản lý"));
                if (nhanVien == null) {
                    return null;
                }
                return nhanVien;
            }
            catch(Exception ex)
            {
                return null;
            }
        }
    }
}