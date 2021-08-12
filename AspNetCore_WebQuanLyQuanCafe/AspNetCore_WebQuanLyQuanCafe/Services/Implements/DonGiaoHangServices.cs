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
    public class DonGiaoHangServices : IDonGiaoHangServices
    {
        private readonly SqlConnectDB _sqlConnectDB;

        public DonGiaoHangServices(SqlConnectDB sqlConnectDB)
        {
            _sqlConnectDB = sqlConnectDB;
        }

        /// <summary>
        /// Get all don giao hang follow user login
        /// </summary>
        /// <returns></returns>
        public async Task<List<DonGiaoHang>> GetAllDonGiaoHangFollowUser(string pSDT)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryDonGiaoHangs = "Select MaGiaoHang,KhachHang.MaKhachHang,MaNhanVien,NgayGiao,DiaChiGiao,TongTien,TrangThai,GhiChu,HoTen,DienThoai" +
                                        " from DONGIAOHANG,KHACHHANG " +
                                        "where KHACHHANG.MAKHACHHANG = DONGIAOHANG.MAKHACHHANG " +
                                        "and KHACHHANG.DIENTHOAI = '" + pSDT + "' " +
                                        "order by ngaygiao desc";
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdDonGiaoHang = cmdDonGiaoHang.ExecuteReader();

                //Get list 
                var result = new List<DonGiaoHang>();
                while (rdDonGiaoHang.Read())
                {
                    DonGiaoHang dgh = new DonGiaoHang()
                    {
                        MaGiaoHang = int.Parse(rdDonGiaoHang[0].ToString()),
                        MaKhachHang = int.Parse(rdDonGiaoHang[1].ToString()),
                        //MaNhanVien = int.Parse(rdDonGiaoHang[2].ToString()),
                        DiaChiGiao = rdDonGiaoHang[4].ToString(),
                        TongTien = Double.Parse(rdDonGiaoHang[5].ToString()),
                        TrangThai = Boolean.Parse(rdDonGiaoHang[6].ToString()),
                        GhiChu = rdDonGiaoHang[7].ToString(),
                        HOTEN = rdDonGiaoHang[8].ToString(),
                        DIENTHOAI = rdDonGiaoHang[9].ToString()
                    };
                    if (DateTime.TryParse(rdDonGiaoHang[3].ToString(), out DateTime dt1)) {
                        dgh.NgayGiao = dt1;
                    }
                    result.Add(dgh);
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch (Exception ex)
            {
                return null;
            }
        }

        public async Task<List<DonGiaoHang>> GetCTGiaoHang(string pSDT)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryDonGiaoHangs = "Select * from DONGIAOHANG,KHACHHANG,CHITIETGIAOHANG, THUCDON " +
                                        "where KHACHHANG.MAKHACHHANG = DONGIAOHANG.MAKHACHHANG and CHITIETGIAOHANG.MATHUCDON = THUCDON.MATHUCDON and CHITIETGIAOHANG.MAGIAOHANG= DONGIAOHANG.MAGIAOHANG " +
                                        "and KHACHHANG.DIENTHOAI = '" + pSDT + "' " +
                                        "order by ngaygiao desc";
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdDonGiaoHang = cmdDonGiaoHang.ExecuteReader();

                //Get list 
                var result = new List<DonGiaoHang>();
                while (rdDonGiaoHang.Read())
                {
                    DonGiaoHang dgh = new DonGiaoHang()
                    {
                        MaGiaoHang = int.Parse(rdDonGiaoHang[0].ToString()),
                        MaKhachHang = int.Parse(rdDonGiaoHang[1].ToString()),
                        MaNhanVien = int.Parse(rdDonGiaoHang[2].ToString()),
                        NgayGiao = DateTime.Parse(rdDonGiaoHang[3].ToString()),
                        DiaChiGiao = rdDonGiaoHang[4].ToString(),
                        TongTien = Double.Parse(rdDonGiaoHang[5].ToString()),
                        TrangThai = Boolean.Parse(rdDonGiaoHang[6].ToString()),
                        GhiChu = rdDonGiaoHang[7].ToString(),
                        MATHUCDON = int.Parse(rdDonGiaoHang[20].ToString()),
                        SOLUONGGIAO = int.Parse(rdDonGiaoHang[21].ToString()),
                        THANHTIEN = Double.Parse(rdDonGiaoHang[22].ToString()),
                        HINHANH = rdDonGiaoHang[27].ToString(),
                        TENMON = rdDonGiaoHang[24].ToString(),
                        DONGIA = int.Parse(rdDonGiaoHang[25].ToString()),
                        HOTEN = rdDonGiaoHang[9].ToString(),
                        DIENTHOAI = rdDonGiaoHang[13].ToString()
                    };
                    result.Add(dgh);
                }

                await _sqlConnectDB.CloseAsync();
                return result;
            }
            catch (Exception ex)
            {
                return null;
            }
        }



        /// <summary>
        /// Get don giao hang details
        /// </summary>
        /// <param name="maDGH"></param>
        /// <returns></returns>
        public async Task<DonGiaoHang> GetDonGiaoHangDetails(int maDGH)
        {
            try
            {
                var donGiaoHang = new DonGiaoHang();
                await _sqlConnectDB.OpenAsync();
                var queryDonGiaoHangs = "Select * from DONGIAOHANG " +
                                        "where maGiaoHang = " + maDGH;
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader rdDonGiaoHang = cmdDonGiaoHang.ExecuteReader();
                while (rdDonGiaoHang.Read())
                {
                    donGiaoHang = new DonGiaoHang()
                    {
                        MaGiaoHang = maDGH,
                        MaKhachHang = int.Parse(rdDonGiaoHang[1].ToString()),
                        MaNhanVien = int.Parse(rdDonGiaoHang[2].ToString()),
                        NgayGiao = DateTime.Parse(rdDonGiaoHang[3].ToString()),
                        DiaChiGiao = rdDonGiaoHang[4].ToString(),
                        TongTien = double.Parse(rdDonGiaoHang[5].ToString()),
                        TrangThai = bool.Parse(rdDonGiaoHang[6].ToString()),
                        GhiChu = rdDonGiaoHang[7].ToString()
                    };
                }

                await _sqlConnectDB.CloseAsync();
                return donGiaoHang;
            }
            catch (Exception ex)
            {
                return null;
            }
        }

        /// <summary>
        /// Create Bill
        /// </summary>
        /// <param name="donGiaoHang"></param>
        /// <returns></returns>
        public async Task<string> CreateDonGiaoHang(DonGiaoHang donGiaoHang)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();
                var queryDonGiaoHangs = String.Format("insert into DonGiaoHang values " +
                                        "({0}, NULL, '{1}', N'{2}', {3}, 0, N'{4}') "
                                        , donGiaoHang.MaKhachHang, DateTime.Now.Date, donGiaoHang.DiaChiGiao, donGiaoHang.TongTien, donGiaoHang.GhiChu);
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                cmdDonGiaoHang.ExecuteNonQuery();

                await _sqlConnectDB.CloseAsync();
                return await GetMaGiaoHangVuaTao();
            }
            catch (Exception ex)
            {
                return null;
            }
        }

        /// <summary>
        /// Create Bill Detail
        /// </summary>
        /// <param name="chiTietGiaoHang"></param>
        /// <returns></returns>
        public async Task<bool> CreateDonGiaoHangDetail (ChiTietGiaoHang chiTietGiaoHang)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();
                var queryDonGiaoHangs = String.Format("insert into ChiTietGiaoHang values " +
                                        "({0},{1},{2},{3})"
                                        , chiTietGiaoHang.MaGiaoHang, chiTietGiaoHang.MaThucDon, chiTietGiaoHang.SLGiao, chiTietGiaoHang.ThanhTien);
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                cmdDonGiaoHang.ExecuteNonQuery();

                await _sqlConnectDB.CloseAsync();
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        #region Private function
        private async Task<string> GetMaGiaoHangVuaTao() {
            try
            {
                await _sqlConnectDB.OpenAsync();
                var queryDonGiaoHangs = "select top 1 maGiaoHang from DonGiaoHang " +
                                        "Order by MAGIAOHANG desc";
                SqlCommand cmdDonGiaoHang = new SqlCommand(queryDonGiaoHangs, _sqlConnectDB.sqlConnection);
                SqlDataReader reader = cmdDonGiaoHang.ExecuteReader();
                while (reader.Read())
                {
                    return reader[0].ToString();   
                }

                await _sqlConnectDB.CloseAsync();
                return null;
            }
            catch (Exception ex)
            {
                return null;
            }
        }
        #endregion
    }
}
