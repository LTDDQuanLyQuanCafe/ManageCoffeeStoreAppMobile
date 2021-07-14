using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface IKhachHangServices
    {
        /// <summary>
        ///   Get all Tai khoan khach hang
        /// </summary>
        Task<IList<KhachHang>> GetAllTaiKhoanKhachHang();

        /// <summary>Gets the tai khoan khach hang information.</summary>
        /// <param name="MaKH">The ma kh.</param>
        /// <returns>
        ///   <br />
        /// </returns>
        Task<KhachHang> GetTaiKhoanKhachHangInfo(int MaKH);
    }
}
