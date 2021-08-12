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
                    KhachHang kh = new KhachHang()
                    {
                        MaKhachHang = rdKhachHang[0].ToString(),
                        TenKhachHang = rdKhachHang[1].ToString(),
                        GioiTinh = rdKhachHang[3].ToString(),
                        Email = rdKhachHang[4].ToString(),
                        DienThoai = rdKhachHang[5].ToString(),
                        DiaChi = rdKhachHang[6].ToString(),
                        DiemTichLuy = int.Parse(rdKhachHang[7].ToString()),
                        HinhAnh = rdKhachHang[8].ToString(),
                        MatKhau = rdKhachHang[9].ToString(),
                    };

                    if (DateTime.TryParse(rdKhachHang[10].ToString(), out DateTime dt1))
                    {
                        kh.NgayTao = dt1;
                    }
                    if (DateTime.TryParse(rdKhachHang[2].ToString(), out DateTime dt2))
                    {
                        kh.NgaySinh = dt2;
                    }
                    result.Add(kh);
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch (Exception ex)
            {
                return null;
            }
        }

        /// <summary>Gets the tai khoan khach hang information.</summary>
        /// <param name="email">The email kh.</param>
        /// <returns>
        ///   <br />
        /// </returns>
        public async Task<KhachHang> GetTaiKhoanKhachHangInfo(string email)
        {
            try
            {
                var open = await _sqlConnectDB.OpenAsync();

                var queryKhachHang = "select * from KhachHang "
                                                + "Where DienThoai = '" + email+"'";
                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHang, _sqlConnectDB.sqlConnection);
                SqlDataReader rdKhachHang = cmdKhachHang.ExecuteReader();

                var result = new KhachHang();
                while (rdKhachHang.Read())
                {
                    result = new KhachHang()
                    {
                        MaKhachHang = rdKhachHang[0].ToString(),
                        TenKhachHang = rdKhachHang[1].ToString(),
                        GioiTinh = rdKhachHang[3].ToString(),
                        Email = rdKhachHang[4].ToString(),
                        DienThoai = rdKhachHang[5].ToString(),
                        DiaChi = rdKhachHang[6].ToString(),
                        DiemTichLuy = int.Parse(rdKhachHang[7].ToString()),
                        HinhAnh = rdKhachHang[8].ToString(),
                        MatKhau = rdKhachHang[9].ToString(),
                        NgayTao = DateTime.Parse(rdKhachHang[10].ToString())
                    };
                    if (DateTime.TryParse(rdKhachHang[10].ToString(), out DateTime dt1))
                    {
                        result.NgayTao = dt1;
                    }
                }
                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch(Exception ex)
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
        public async Task<bool> InsertTaiKhoanKhachHangInfo(CreateRegisterRequest kh)
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

        /// <summary>
        /// Update information customer
        /// </summary>
        /// <param name="kh"></param>
        /// <returns></returns>
        public async Task<bool> UpdateInfoCustomer(KhachHang _kh)
        {
            try
            {
                KhachHang kh = await GetTaiKhoanKhachHangInfo(_kh.Email);
                
                await _sqlConnectDB.OpenAsync();

                if (kh == null) {
                    return false;
                }

                if (!String.IsNullOrEmpty(_kh.TenKhachHang) && !kh.TenKhachHang.Equals(_kh.TenKhachHang)) {
                    kh.TenKhachHang = _kh.TenKhachHang;
                }
                if (_kh.NgaySinh.HasValue && !kh.NgaySinh.Equals(_kh.NgaySinh))
                {
                    kh.NgaySinh = _kh.NgaySinh;
                }
                if (!String.IsNullOrEmpty(_kh.GioiTinh) && !kh.GioiTinh.Equals(_kh.GioiTinh))
                {
                    kh.GioiTinh = _kh.GioiTinh;
                }
                if (!String.IsNullOrEmpty(_kh.DienThoai) && !kh.DienThoai.Equals(_kh.DienThoai))
                {
                    if (!await CheckInfomationExist(_kh.Email, _kh.DienThoai)) {
                        kh.DienThoai = _kh.DienThoai;
                    }
                }
                if (!String.IsNullOrEmpty(_kh.DiaChi) && kh.DiaChi.Equals(_kh.DiaChi))
                {
                    kh.DiaChi = _kh.DiaChi;
                }

                var queryKhachHang = String.Format("Update KhachHang " +
                                        "set HoTen = N'{0}', " +
                                        "NgaySinh = '{1}', " +
                                        "GioiTinh = N'{2}', " +
                                        "DienThoai = '{3}', " +
                                        "DiaChi = N'{4}'," +
                                        "HinhAnh = '{5}' " +
                                        "Where Email ='{6}'", kh.TenKhachHang, kh.NgaySinh, kh.GioiTinh,
                                            kh.DienThoai, kh.DiaChi, kh.HinhAnh, kh.Email);

                SqlCommand cmdKhachHang = new SqlCommand(queryKhachHang, _sqlConnectDB.sqlConnection);
                cmdKhachHang.ExecuteNonQuery();

                await _sqlConnectDB.CloseAsync();

                return true;
            }
            catch(Exception ex)
            {
                return false;
            }
        }

    }
}
