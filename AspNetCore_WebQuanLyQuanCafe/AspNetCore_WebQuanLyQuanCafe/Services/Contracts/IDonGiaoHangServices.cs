using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface IDonGiaoHangServices 
    {
        /// <summary>
        /// Get all don giao hang follow user login
        /// </summary>
        /// <returns></returns>
        Task<List<DonGiaoHang>> GetAllDonGiaoHangFollowUser(string email);

        Task<List<DonGiaoHang>> GetCTGiaoHang(string email);

        /// <summary>
        /// Get don giao hang details
        /// </summary>
        /// <param name="maDGH"></param>
        /// <returns></returns>
        Task<DonGiaoHang> GetDonGiaoHangDetails(int maDGH);

        /// <summary>
        /// Create Bill
        /// </summary>
        /// <param name="donGiaoHang"></param>
        /// <returns></returns>
        Task<string> CreateDonGiaoHang(DonGiaoHang donGiaoHang);


        /// <summary>
        /// Create Bill Detail
        /// </summary>
        /// <param name="chiTietGiaoHang"></param>
        /// <returns></returns>
        Task<bool> CreateDonGiaoHangDetail(ChiTietGiaoHang chiTietGiaoHang);
    }
}
