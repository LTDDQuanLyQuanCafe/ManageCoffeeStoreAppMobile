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
        /// Kiem tra mat khau cu
        /// </summary>
        /// <param name="mkCu"></param>
        /// <param name="tenTaiKhoan"></param>
        /// <returns></returns>
        Task<bool> KiemTraMKCu(string tenTaiKhoan, string mkCu);

        /// <summary>
        /// Tao tai khoan khach hang
        /// </summary>
        /// <param name="tk"></param>
        /// <returns></returns>
        Task<bool> InsertTK(TaiKhoanKhachHang tk);
    }
}
