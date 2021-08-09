using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using AspNetCore_WebQuanLyQuanCafe.Models.Request;
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

        /// <summary>
        /// Check account information be exist
        /// </summary>
        /// <param name="email"></param>
        /// <param name="phone"></param>
        /// <returns></returns>
        Task<bool> CheckInfomationExist(string email, string phone);

        /// <summary>
        /// Insert one customer
        /// </summary>
        /// <param name="kh"></param>
        /// <returns></returns>
        Task<bool> InsertTaiKhoanKhachHangInfo(CreateRegisterRequest kh);

        /// <summary>
        /// Lay ma khach hang 
        /// </summary>
        /// <param name="dienThoai"></param>
        /// <returns></returns>
        Task<string> LayMaKH(string dienThoai);

        /// <summary>
        /// Update information customer
        /// </summary>
        /// <param name="kh"></param>
        /// <returns></returns>
        Task<bool> UpdateInfoCustomer(KhachHang kh);
    }
}
