using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DonGiaoHangController : ControllerBase
    {
        private readonly IDonGiaoHangServices _donGiaoHangServices;
        public DonGiaoHangController(IDonGiaoHangServices donGiaoHangServices)
        {
            _donGiaoHangServices = donGiaoHangServices;
        }

        // GET: get/email
        [HttpGet]
        [Route("get/{email}")]
        public IActionResult GetBillFollowUser(string email)
        {
            var listBills = _donGiaoHangServices.GetAllDonGiaoHangFollowUser(email);
            return Ok(listBills.Result);
        }

        // GET: details/idBill
        [HttpGet]
        [Route("details/{idBill}")]
        public IActionResult GetBillDetails(int idBill)
        {
            var listBill = _donGiaoHangServices.GetDonGiaoHangDetails(idBill);
            return Ok(listBill.Result);
        }
    }
}
