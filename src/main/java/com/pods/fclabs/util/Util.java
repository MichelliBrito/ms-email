package com.pods.fclabs.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.pods.fclabs.enums.LoggerInfoLevelEnum;
import com.pods.fclabs.models.MsgRetorno;
import com.pods.fclabs.models.Usuario;
import com.pods.fclabs.models.UsuarioResponse;



@Service
public class Util {
    private static final String PODS_MAPPING = "/usuario";

    private Util() {
    }

    public static Date formatarData(Date dtNascimento) {
        if (Objects.isNull(dtNascimento)) {
            return null;
        }
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0", locale);
        df.setTimeZone(TimeZone.getTimeZone("Brasil/East"));
        String format = df.format(dtNascimento.getTime());
        try {
            return df.parse(format);
        } catch (ParseException e) {
            registraLog(Util.class, e.getClass().getName(), "formatarData", "Erro ao formatar data!", null, LoggerInfoLevelEnum.ERROR);
        }

        return null;
    }

    public static String getEnumConverterExceptionMessage(RuntimeException ex) {
        String rootCauseMessage = ExceptionUtils.getRootCauseMessage(ex);
        String[] split = rootCauseMessage.split("EnumConverterException: ");

        if (split.length > 1) {
            return split[1];
        }

        return split[0];
    }

    /**
     * @param date
     * @param locale Metodo que valida a data com dia mes e ano, separado por Barra
     */
    private static boolean isDataValidaBarraDiaMesAno(Date date, Locale locale) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 00:00:00.0", locale);
            String format = sdf.format(date.getTime());
            sdf.setLenient(false);
            sdf.parse(format);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param date
     * @param locale Metodo que valida a data com ano mes e dia separado por Barra
     */
    private static boolean isDataValidaBarraAnoMesDia(Date date, Locale locale) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00.0", locale);
            String format = sdf.format(date.getTime());
            sdf.setLenient(false);
            sdf.parse(format);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param date
     * @param locale Metodo que valida a data com dia mes e ano, separado por hífen
     */
    private static boolean isDataValidaHifenDiaMesAno(Date date, Locale locale) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy 00:00:00.0", locale);
            String format = sdf.format(date.getTime());
            sdf.setLenient(false);
            sdf.parse(format);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param date
     * @param locale Metodo que Valida a data com ano mes e dia separado por hífen
     */
    private static boolean isDataValidaHifenAnoMesDia(Date date, Locale locale) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0", locale);
            String format = sdf.format(date.getTime());
            sdf.setLenient(false);
            sdf.parse(format);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param date
     * @param locale Metodo que devolve um SimpleDateFormat com uma mascara conforme validacao
     * @see br.com.dasa.mdmusuario.util.Util#isDataValidaBarraDiaMesAno(Date, Locale)
     * @see br.com.dasa.mdmusuario.util.Util#isDataValidaBarraAnoMesDia(Date, Locale)
     * @see br.com.dasa.mdmusuario.util.Util#isDataValidaHifenDiaMesAno(Date, Locale)
     * @see br.com.dasa.mdmusuario.util.Util#isDataValidaHifenAnoMesDia(Date, Locale)
     */
    public static SimpleDateFormat setFormatEqualsMask(Date date, Locale locale) {
        SimpleDateFormat sdf = null;

        if (isDataValidaBarraDiaMesAno(date, locale)) {
            sdf = new SimpleDateFormat("dd/MM/yyyy 00:00:00.0", locale);
        } else if (isDataValidaBarraAnoMesDia(date, locale)) {
            sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00.0", locale);
        } else if (isDataValidaHifenDiaMesAno(date, locale)) {
            sdf = new SimpleDateFormat("dd-MM-yyyy 00:00:00.0", locale);
        } else if (isDataValidaHifenAnoMesDia(date, locale)) {
            sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0", locale);
        }

        return sdf;

    }



    public static String formateDate(Date dateString) {
        if (Objects.nonNull(dateString))
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dateString);

        return StringUtils.EMPTY;
    }

    public static String aplicarMascaraEmail(String email) {
        String nickEmail = email.substring(0, email.indexOf("@"));
        Double porcentagem = nickEmail.length() * 0.40;
        int i = porcentagem.intValue();
        String emailMascarado = email.replaceAll("(?<=.{" + i + "}).(?=[^@]*?@)", "\\*");
        return emailMascarado;
    }

    public static String aplicarMascaraCelular(String celular) {
        String celularMascarado = celular.replaceAll("(?<=.{4}).(?=.{5})", "\\*");
        return celularMascarado;
    }

    public static String aplicarFormatacaoCelular(String celular) {
        String celularParenteses = celular.replaceAll("^(\\d{2})(\\d)", "($1)$2");
        String celularHifen = celularParenteses.replaceAll("(\\d)(\\d{4})$", "$1-$2");
        return celularHifen;
    }

    public static Boolean validarCPF(String cpf) {
        if (cpf.length() > 11)
            return Boolean.FALSE;

        try {
            Double.parseDouble(cpf);
        } catch (NumberFormatException nfe) {
            return Boolean.FALSE;
        }

        String numero = StringUtils.leftPad(cpf, 11, "0");

        String d1 = numero.substring(9, 10);
        String d2 = numero.substring(10, 11);

        if (d1.equalsIgnoreCase(calcularDigitoVerificador(numero.substring(0, 9))))
            if (d2.equalsIgnoreCase(calcularDigitoVerificador(numero.substring(0, 10))))
                return Boolean.TRUE;

        return Boolean.FALSE;
    }

    private static String calcularDigitoVerificador(String cpf) {
        int initFactor = cpf.length() + 1;
        int sum = 0;
        int remainder;

        for (Character numero : cpf.toCharArray()) {
            sum += Integer.parseInt(numero.toString()) * initFactor;
            initFactor--;
        }

        remainder = sum % 11;
        if (remainder <= 1)
            return "0";

        return String.valueOf(11 - remainder);
    }

    public static MsgRetorno criaMsgRetorno(Integer status, String error, String message, String exceptionClass) {
        return MsgRetorno.Builder.create()
                .timestamp(new Date(System.currentTimeMillis()))
                .status(status)
                .error(error)
                .exception(exceptionClass)
                .message(message)
                .path(PODS_MAPPING)
                .build();
    }

    public static MsgRetorno criaMsgRetornoComUsuario(Integer status, String error, String message, String exceptionClass, Usuario usuario) {
        return MsgRetorno.Builder.create()
                .timestamp(new Date(System.currentTimeMillis()))
                .status(status)
                .error(error)
                .exception(exceptionClass)
                .message(message)
                .path(PODS_MAPPING)
                .usuario(usuario)
                .build();
    }


    public static void registraLog(Class classe, String exception, String method, String message, Object payload, LoggerInfoLevelEnum loggerLevel) {
        Logger log = LoggerFactory.getLogger(classe);
        String logMessage = MessageFormat.format("{0}.{1}(): {2} -> {3}", classe.getSimpleName(), method, exception, message);
        if (Objects.nonNull(payload))
            logMessage = MessageFormat.format(logMessage.concat("\nPayload: {0}")
                    , new GsonBuilder().setPrettyPrinting().create().toJson(payload));

        if (LoggerInfoLevelEnum.DEBUG == loggerLevel) {
            log.debug(logMessage);
        } else if (LoggerInfoLevelEnum.INFO == loggerLevel) {
            log.info(logMessage);
        } else {
            log.error(logMessage);
        }
    }
    
    
    public UsuarioResponse converteUsuarioInResponse(Usuario paciente) {
        UsuarioResponse pr = new UsuarioResponse();
        pr.setId(paciente.getId());
        pr.setNome(paciente.getNome());
        pr.setNomeMae(paciente.getNomeMae());
     
        return pr;
    }
    
    public List<UsuarioResponse> converteListUsuarioInResponse(List<Usuario> listaUsuario) {
        List<UsuarioResponse> lista = new ArrayList<>();
        for (int i = 0; i < listaUsuario.size(); i++) {
        	UsuarioResponse pr = new UsuarioResponse();
            pr.setId(listaUsuario.get(i).getId());
            pr.setNome(listaUsuario.get(i).getNome());
            pr.setNomeMae(listaUsuario.get(i).getNomeMae());
            lista.add(pr);
		}
    
        return lista;
    }
    
}
