using AspNetCore_WebQuanLyQuanCafe.Models.ConnectSQL;
using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Models.Request;
using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Implements
{
    public class KhachHangServices : IKhachHangServices
    {
        private readonly SqlConnectDB _sqlConnectDB;

        public KhachHangServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }

        /// <summary>
        ///   Get all Tai khoan khach hang
        /// </summary>
        public async Task<IList<KhachHang>> GetAllTaiKhoanKhachHang()
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryKhachHangs = "select * from KhachHang";
                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdKhachHang = cmdKhachHang.ExecuteReader();

                //Get list 
                var result = new List<KhachHang>();
                while (rdKhachHang.Read())
                {
                    result.Add(new KhachHang()
                    {
                        MaKhachHang = rdKhachHang[0].ToString(),
                        TenKhachHang = rdKhachHang[1].ToString(),
                        NgaySinh = DateTime.Parse(rdKhachHang[2].ToString()),
                        GioiTinh = rdKhachHang[3].ToString(),
                        Email = rdKhachHang[4].ToString(),
                        DienThoai = rdKhachHang[5].ToString(),
                        MaLoaiKH = rdKhachHang[6].ToString(),
                        DiaChi = rdKhachHang[7].ToString(),
                        DiemTichLuy = int.Parse(rdKhachHang[8].ToString()),
                        HinhAnh = rdKhachHang[9].ToString()
                    });
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch
            {
                return null;
            }
        }

        /// <summary>Gets the tai khoan khach hang information.</summary>
        /// <param name="MaKH">The ma kh.</param>
        /// <returns>
        ///   <br />
        /// </returns>
        public async Task<KhachHang> GetTaiKhoanKhachHangInfo(int MaKH)
        {
            try
            {
                var open = await _sqlConnectDB.OpenAsync();

                var queryKhachHang = "select * from KhachHang "
                                                + "Where maKhachHang = " + MaKH;
                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHang, _sqlConnectDB.sqlConnection);
                SqlDataReader rdKhachHang = cmdKhachHang.ExecuteReader();

                var result = new KhachHang();
                while (rdKhachHang.Read())
                {
                    result = new KhachHang()
                    {
                        MaKhachHang = rdKhachHang[0].ToString(),
                        TenKhachHang = rdKhachHang[1].ToString(),
                        NgaySinh = DateTime.Parse(rdKhachHang[2].ToString()),
                        GioiTinh = rdKhachHang[3].ToString(),
                        Email = rdKhachHang[4].ToString(),
                        DienThoai = rdKhachHang[5].ToString(),
                        MaLoaiKH = rdKhachHang[6].ToString(),
                        DiaChi = rdKhachHang[7].ToString(),
                        DiemTichLuy = int.Parse(rdKhachHang[8].ToString()),
                        HinhAnh = rdKhachHang[9].ToString()
                    };
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Check account information be exist
        /// </summary>
        /// <param name="email"></param>
        /// <param name="phone"></param>
        /// <returns></returns>
        public async Task<bool> CheckInfomationExist(string email, string phone)
        {
            try
            {
                var open = await _sqlConnectDB.OpenAsync();

                var queryExists = "select count(*) from KhachHang "
                                                + "Where Email = '" + email + "' or DienThoai='" + phone + "'";
                SqlCommand cmdKhachHang = new SqlCommand(queryExists, _sqlConnectDB.sqlConnection);
                int check = (int)cmdKhachHang.ExecuteScalar();
                if (check < 1)
                {
                    return false;
                }
                return true;
            }
            catch
            {
                return false;
            }
        }

        /// <summary>
        /// Insert one customer
        /// </summary>
        /// <param name="kh"></param>
        /// <returns></returns>
        public async Task<bool> InsertTaiKhoanKhachHangInfo(CreateKhachHangRequest kh)
        {
            try
            {
                var open = await _sqlConnectDB.OpenAsync();

                var queryKhachHang = String.Format("insert into khachhang(HoTen,DienThoai,Email) values" +
                                                        "(N'{0}','{1}','{2}')", kh.HoTen, kh.DienThoai, kh.Email);
                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHang, _sqlConnectDB.sqlConnection);
                cmdKhachHang.ExecuteNonQuery();

                await _sqlConnectDB.CloseAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        /// <summary>
        /// Lay ma khach hang 
        /// </summary>
        /// <param name="dienThoai"></param>
        /// <returns></returns>
        public async Task<string> LayMaKH(string dienThoai)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryKhachHang = String.Format("select * from KhachHang"
                                                + " where dienThoai='" + dienThoai + "'", dienThoai);
                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHang, _sqlConnectDB.sqlConnection);
                SqlDataReader rdKhachHang = cmdKhachHang.ExecuteReader();

                while (rdKhachHang.Read())
                {
                    return rdKhachHang[0].ToString();
                }

                await _sqlConnectDB.CloseAsync();
                return null;
            }
            catch
            {
                return null;
            }
        }

    }
}
