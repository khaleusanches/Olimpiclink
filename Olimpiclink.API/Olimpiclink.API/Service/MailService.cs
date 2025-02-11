using System.Net;
using System.Net.Mail;

namespace Olimpiclink.API.Service
{
    public class MailService
    {
        private string smtpAdress => "smtp.gmail.com";
        private int portNumber => 587;
        private string emailFromAdress => "hauleksm@gmail.com";
        private string password => "kobd gvfb jmis vylm";

        public void SendEmail(string email, string assunto, string body, bool isHtml = false)
        {
            using (MailMessage mailMessage = new MailMessage())
            {
                mailMessage.From = new MailAddress(emailFromAdress);
                mailMessage.To.Add(email);
                mailMessage.Subject = assunto;
                mailMessage.Body = body;
                mailMessage.IsBodyHtml = isHtml;
                using (SmtpClient smtp = new SmtpClient(smtpAdress, portNumber))
                {
                    smtp.EnableSsl = true;
                    smtp.UseDefaultCredentials = false;
                    smtp.Credentials = new NetworkCredential(emailFromAdress, password);
                    smtp.Send(mailMessage);
                };
            };
        }
    }
}
