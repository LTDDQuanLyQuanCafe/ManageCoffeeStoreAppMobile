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
        public async Task<List<DonGiaoHang>> GetAllDonGiaoHangFollowUser(string email)
        {
            try
            {
                await _sqlConnectDB.OpenAsync();

                var queryDonGiaoHangs = "Select * from DONGIAOHANG,KHACHHANG " +
                                        "where KHACHHANG.MAKHACHHANG = DONGIAOHANG.MAKHACHHANG " +
                                        "and KHACHHANG.EMAIL = '" + email + "' " +
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
                        GhiChu = rdDonGiaoHang[7].ToString()
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

    }
}
