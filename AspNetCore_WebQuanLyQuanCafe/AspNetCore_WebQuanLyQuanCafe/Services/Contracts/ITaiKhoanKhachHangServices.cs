using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface ITaiKhoanKhachHangServices
    {
        /// <summary>
        /// Kiem tra dang nhap.
        /// </summary>
        /// <param name="tenTaiKhoan">The ten tai khoan.</param>
        /// <param name="matKhau">The mat khau.</param>
        /// <returns></returns>
        Task<bool> KiemTraDangNhap(string tenTaiKhoan, string matKhau);

        /// <summary>
        /// Kiem tra email da ton tai 
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        Task<bool> KiemTraEmailTonTai(string email);

        /// <summary>
        /// Get email 
        /// </summary>
        /// <param name="email"></param>
        /// <returns></returns>
        Task<string> GetEmail(string sdt);
    }
}
