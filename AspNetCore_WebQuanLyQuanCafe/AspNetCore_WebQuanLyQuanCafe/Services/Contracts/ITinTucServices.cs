using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface ITinTucServices
    {
        /// <summary>
        /// Get all tin tucs
        /// </summary>
        /// <returns></returns>
        Task<List<TinTuc>> GetAllTinTucs();

        /// <summary>
        /// Get tin tuc details
        /// </summary>
        /// <returns></returns>
        Task<TinTuc> GetTinTucDetails(int idNew);
    }
}
