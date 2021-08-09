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

        /// <summary>
        /// Get don giao hang details
        /// </summary>
        /// <param name="maDGH"></param>
        /// <returns></returns>
        Task<DonGiaoHang> GetDonGiaoHangDetails(int maDGH);
    }
}
