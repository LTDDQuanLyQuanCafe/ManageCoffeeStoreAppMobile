using AspNetCore_WebQuanLyQuanCafe.Models.ConnectSQL;
using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Implements
{
    public class TaiKhoanKhachHangServices : ITaiKhoanKhachHangServices
    {
        private readonly SqlConnectDB _sqlConnectDB;

        public TaiKhoanKhachHangServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }
        /// <summary>
        /// Kiem tra dang nhap.
        /// </summary>
        /// <param name="tenTaiKhoan">The ten tai khoan.</param>
        /// <param name="matKhau">The mat khau.</param>
        /// <returns></returns>
        public async Task<bool> KiemTraDangNhap(string tenTaiKhoan, string matKhau)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryTaiKhoanKhachHangs = "select * from KhachHang "
                                        + "where email = '" + tenTaiKhoan + "' and matkhau='" + matKhau + "'";
                SqlCommand cmdTaiKhoanKhachHang = new SqlCommand(queryTaiKhoanKhachHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdTaiKhoanKhachHang = cmdTaiKhoanKhachHang.ExecuteReader();

                if (!rdTaiKhoanKhachHang.HasRows)
                {
                    return false;
                }

                await _sqlConnectDB.CloseAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        /// <summary>
        /// Kiem tra email da ton tai 
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        public async Task<bool> KiemTraEmailTonTai(string email)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryTaiKhoanKhachHangs = String.Format("select * from khachhang"
                                                + " where email = '{0}'", email);
                SqlCommand cmdTaiKhoanKhachHang = new SqlCommand(queryTaiKhoanKhachHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdTaiKhoanKhachHang = cmdTaiKhoanKhachHang.ExecuteReader();

                if (!rdTaiKhoanKhachHang.HasRows)
                {
                    return false;
                }

                await _sqlConnectDB.CloseAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
