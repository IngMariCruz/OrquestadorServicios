package co.com.vanegas.microservice.resolveEnigmaApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetEnigmaStepResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class GetEnigmaStepResponse   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("step")
  private Integer step = null;

  @JsonProperty("answer")
  private String answer = null;

  public GetEnigmaStepResponse header(Header header) {
    this.header = header;
    return this;
  }

  /**
   * Get header
   * @return header
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public GetEnigmaStepResponse answer(String answer) {
    this.answer = answer;
    return this;
  }

  public GetEnigmaStepResponse step(Integer step) {
    this.step = step;
    return this;
  }

  public void setStep(Integer step) {
    this.step = step;
  }

  /**
   * Get answer
   * @return answer
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEnigmaStepResponse getEnigmaStepResponse = (GetEnigmaStepResponse) o;
    return Objects.equals(this.header, getEnigmaStepResponse.header) &&
            Objects.equals(this.answer, getEnigmaStepResponse.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, answer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEnigmaStepResponse {\n");

    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
    sb.append("    description: ").append(toIndentedString(answer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
