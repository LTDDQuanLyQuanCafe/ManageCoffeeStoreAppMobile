using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface ILoaiTDServices
    {
        /// <summary>
        ///   Get all Loai thuc don
        /// </summary>
        Task<IList<LoaiTD>> GetAllLoaiTD();
        /// 
    }
}
