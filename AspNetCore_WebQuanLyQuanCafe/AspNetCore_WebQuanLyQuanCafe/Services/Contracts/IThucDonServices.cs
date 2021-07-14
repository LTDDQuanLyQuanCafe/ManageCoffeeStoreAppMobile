using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Services.Contracts
{
    public interface IThucDonServices
    {
        /// <summary>
        /// Gets all thuc don.
        /// </summary>
        /// <returns><br/></returns>
        Task<IList<ThucDon>> GetAllThucDon();
    }
}
