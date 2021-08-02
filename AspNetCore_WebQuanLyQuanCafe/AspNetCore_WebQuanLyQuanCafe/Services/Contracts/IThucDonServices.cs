using AspNetCore_WebQuanLyQuanCafe.Models.Domain;
using System.Collections.Generic;
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


        /// <summary>
        /// Gets List Thuc Dons follow Loai Thuc Don
        /// </summary>
        /// <param name="maLoaiThucDon"></param>
        /// <returns></returns>
        Task<IList<ThucDon>> GetThucDonFollowLoaiTD(string maLoaiThucDon);

        /// <summary>
        /// Gets List Thuc Don detail
        /// </summary>
        /// <param name="maThucDon"></param>
        /// <returns></returns>
        Task<ThucDon> GetThucDonDetail(string maThucDon);
    }
}
